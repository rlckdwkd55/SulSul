package com.sulsulmarket.sulsul.payment.service;

import com.sulsulmarket.sulsul.orders.service.OrdersService;
import com.sulsulmarket.sulsul.payment.dao.KakaoPayDao;
import com.sulsulmarket.sulsul.orders.dto.Orders;
import com.sulsulmarket.sulsul.payment.dto.*;
import com.sulsulmarket.sulsul.product.dao.ProductDao;
import com.sulsulmarket.sulsul.product.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.parser.ParserImpl;

import java.util.Map;

@Service
@Slf4j
public class KakaoPayService {

    @Autowired
    private KakaoPayDao kakaoPayDao;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductDao productDao;
    private KakaoReadyResponse response;
    private ApprovedCancelAmount cancelAmount;

    @Value("${kakao.pay.cid}")
    private String cid;
    @Value("${kakao.pay.admin_key}")
    private String adminKey;
    @Value("${kakao.pay.partner_order_id}")
    private String partnerOrderId;
    @Value("${kakao.pay.partner_user_id}")
    private String partnerUserId;
    @Value("${kakao.pay.approval_url}")
    private String approvalUrl;
    @Value("${kakao.pay.fail_url}")
    private String failUrl;
    @Value("${kakao.pay.cancel_url}")
    private String cancelUrl;

    /**
     * 결제요청
     */
    public KakaoReadyResponse kakaoReady(int orderNo, int productNo, int quantity) {
        log.info("ORDER_NO -> [{}], PRODUCT_NO -> [{}], Quantity -> {}", orderNo, productNo, quantity);
        // 주문내역(Order객체)이 없는데 결제가 이루어지면 안되기 때문에
        // 주문내역에 존재하는 내역인지 확인 후 결제가 이루어져야한다
        // 그 이후에 결제요청 메소드가 돌아야한다.
        Orders orderData = ordersService.getOrderData(orderNo);

        if (orderData == null) {
            return null;
        }

        // 위에서 검증이 끝난 후
        // 아래 코드 작동
        Product product = productDao.getProductByProductNo(productNo);

        if (product == null) {
            return null;
        }

        // 결제 요청하는 제품의 수량이 실제로 존재 하는지 검증
        if (product.getPRODUCT_AMOUNT() == 0) {
            return null;
        }

        log.info("PRODUCT -> [{}]", product.toString());

        String productName = product.getPRODUCT_NAME();
        int price = product.getPRODUCT_PRICE();
        int totalAmount = price * quantity;
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", partnerOrderId);
        parameters.add("partner_user_id", partnerUserId);
        parameters.add("item_name", productName);
        parameters.add("quantity", quantity);
        parameters.add("total_amount", totalAmount);
        parameters.add("tax_free_amount", 0);
        parameters.add("approval_url", approvalUrl); // 성공 시 redirect url
        parameters.add("cancel_url", failUrl); // 취소 시 redirect url
        parameters.add("fail_url", cancelUrl); // 실패 시 redirect url

        // 우리가 보유한 재고보다 결제하는 수량이 많을경우 결제 취소
        if (product.getPRODUCT_AMOUNT() < quantity) {
            return null;
        }

        log.info("MultiValueMap -> [{}]", parameters);
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        response = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
        log.info("Kakao Response -> [{}]", response.toString());

        if (response.getTid() != null) {
            kakaoPayDao.putTid(response.getTid(), String.valueOf(orderNo));
        }

        // response 객체에 담겨져 있는 값은 TID, REDIRECT_URL
            return response;
    }

    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse approveResponse(@RequestBody Map<String, Object> paymentMap) {

        String tid = (String) paymentMap.get("tid");
        String pgToken = (String) paymentMap.get("pgToken");

        // 카카오 요청
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", tid);
        parameters.add("partner_order_id", partnerOrderId);
        parameters.add("partner_user_id", partnerUserId);
        parameters.add("pg_token", pgToken);


        log.info("SuccessMultiValueMap -> [{}]", parameters);
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse response = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);



//        if(response != null) {
//            kakaoPayDao.approveInsert(pgToken, tid);
//            // dao <= 데이터 베이스 적재(Insert),
//        } else{
//            log.error("Request KKO approveResponse Tid : {}, PG_TOKEN : {}", tid, pgToken);
//        }


        log.info("Success -> [{}]", response.toString());

//        if (response.getTid() != null) {
//            kakaoPayDao.putTid(response.getTid());
//        }

        return response;
    }

    /**
     * 결제 환불
     */
    public KakaoCancelResponse kakaoCancel(int orderNo) {

        Orders orderData = ordersService.getOrderData(orderNo);

        if (orderData == null) {
            return null;
        }

        // order_detail에 있는 가격을 가져와야한다.
        // 카카오페이 요청
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", response.getTid());
        parameters.add("cancel_amount", cancelAmount.getTotal());
        parameters.add("cancel_tax_free_amount", 0);

        log.info("SuccessMultiValueMap -> [{}]", parameters);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse response = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class);

        log.info("Success -> [{}]", response.toString());

        if (response.getTid() != null) {
            kakaoPayDao.cancelTid(response.getTid(), String.valueOf(orderNo));
        }

        return response;
    }

    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders () {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + adminKey;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }



}
