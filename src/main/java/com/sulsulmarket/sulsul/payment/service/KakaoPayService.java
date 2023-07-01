package com.sulsulmarket.sulsul.payment.service;

import com.sulsulmarket.sulsul.payment.dao.KakaoPayDao;
import com.sulsulmarket.sulsul.payment.dto.ApprovedCancelAmount;
import com.sulsulmarket.sulsul.payment.dto.KakaoApproveResponse;
import com.sulsulmarket.sulsul.payment.dto.KakaoCancelResponse;
import com.sulsulmarket.sulsul.payment.dto.KakaoReadyResponse;
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
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@Service
@Slf4j
public class KakaoPayService {

    @Autowired
    private KakaoPayDao kakaoPayDao;

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
    public KakaoReadyResponse kakaoReady(int productNo, int quantity) {
        log.info("PRODUCT_NO -> [{}], Quantity -> {}", productNo, quantity);
        Product product = productDao.getProductByProductNo(productNo);

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

        return response;
        // response 객체에 담겨져 있는 값은 TID, REDIRECT_URL
    }

    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse approveResponse(String pgToken, int tid) {

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

        return response;
    }

    /**
     * 결제 환불
     */
    public KakaoCancelResponse kakaoCancel() {

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
