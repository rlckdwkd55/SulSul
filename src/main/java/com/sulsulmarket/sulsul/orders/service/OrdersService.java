package com.sulsulmarket.sulsul.orders.service;

import com.sulsulmarket.sulsul.Util.SulSulUil;
import com.sulsulmarket.sulsul.orders.dao.OrdersDao;
import com.sulsulmarket.sulsul.orders.dto.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
@Slf4j
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Transactional
    public int insertOrders(Map<String, Object> requestOrder){

        int result = 0;

        String memberId = (String)requestOrder.get("memberId");
        Date orderDate = SulSulUil.getCurrentTime();
        String orderAddress = (String)requestOrder.get("orderAddress");
        String orderReceiver =(String) requestOrder.get("orderReceiver");
        String orderRequest = (String)requestOrder.get("orderRequest");
        String orderPhone = (String)requestOrder.get("orderPhone");
        String payMethod = (String)requestOrder.get("payMethod");
        log.info("갖고 오는 값을 다 찍어보자 씨팔 :, memberId :{}, orderAddress:{}, orderReceiver:{}, orderRequest:{}, orderPhone:{}, payMethod:{} "
                , memberId, orderAddress,orderReceiver,orderRequest,orderPhone, payMethod);
        List<Map<String,Object>> orderDetailList = (List<Map<String,Object>>)requestOrder.get("orderDetailList");
        //프론트는 Product를 몰라, Product는 Back end에서 Database에 적재하기 쉽게 하기 위해 생성한 객체야
        //프론트와 소통하기 위한 규격이 필요해 이해해?

        Map<String, Object> orderParameter = new HashMap<>();

        int orderNo = Math.toIntExact(SulSulUil.getNextSequence());
        // TODO 생성된 orderNo 값을 가지고 orders table에서 조회


        orderParameter.put("ORDER_NO", orderNo);
        orderParameter.put("MEMBER_ID", memberId);
        orderParameter.put("ORDER_DATE", String.valueOf(orderDate));
        orderParameter.put("ORDER_ADDRESS", orderAddress);
        orderParameter.put("ORDER_RECEIVER", orderReceiver);
        orderParameter.put("ORDER_REQUEST", orderRequest);
        orderParameter.put("ORDER_PHONE", orderPhone);
        orderParameter.put("PAY_METHOD", payMethod);
        //orders 테이블 insert하고

        int ordersResult = ordersDao.getInsertOrders(orderParameter);

        // 1. List와 Map의 개념정리
        // 2, List와 Map 반드시 다뤄보기 // 금요일 할당치
        // 3. 어떤 키로 어떤 값이 있어야할지(규격 정하기) // 여기까지 해도 좋아
        // 4. 로직구현
        // @Transaction

        if(ordersResult == 1){
            if(orderDetailList.size() > 0) {
                for(int i = 0; i < orderDetailList.size(); i++) {
                    Map<String,Object> orderDetailMap = orderDetailList.get(i);

                    Map<String,Object> detailParameter = new HashMap<>();
                    int productNo = (Integer.parseInt((String) orderDetailMap.get("productNo")));
                    int detailAmount = (Integer.parseInt((String)orderDetailMap.get("detailAmount")));
                    int detailPrice = (Integer.parseInt((String)orderDetailMap.get("detailPrice")));
                    String payDate = (String)orderDetailMap.get("payDate");

                    detailParameter.put("DETAIL_NO", i);
                    detailParameter.put("ORDER_NO", orderNo);
                    detailParameter.put("PRODUCT_NO", productNo);
//                    detailParameter.put("DELIVERY_NO", null);// 배송 하기 전
//                    detailParameter.put("PROCESS_CODE",);
                    detailParameter.put("DETAIL_AMOUNT", detailAmount); // 수량(프론트에서 보내 준 데이터를
                    detailParameter.put("DETAIL_PRICE", detailPrice); // 가격(product의 가격을 가져와서 수량(detail_amount) 곱하기)
                    detailParameter.put("PAY_DATE", payDate); // 결제일

                    int orderDetailResult = ordersDao.getInsertOrderDetail(detailParameter);
                    if(orderDetailResult == 1){

                    } else{
                        //orderDetail Insert도 롤백
                        //orders insert도 롤백
                        //그리고 return은 실패
                        return 0;
                    }
                }

//                for(Product product : orderDetailList) {
//                    Map<String,Object> detailParameter = new HashMap<>();
//                    int idx = orderDetailList.indexOf(product);
//                    detailParameter.put("DETAIL_NO", idx);
//
//                    int orderDetailResult = ordersDao.insertOrderDetail(detailParameter);
//                    if(orderDetailResult == 1){
//
//                    } else{
//                        //orderDetail Insert도 롤백
//                        //orders insert도 롤백
//                        //그리고 return은 실패
//
//                        return 0;
//
//                    }
//                }
                result = 1;
            }

        } else{
            log.error("Orders Insert Failed ...");
        }


        return result;
    }

    public Orders getOrderData(int orderNo){

        return ordersDao.getOrderData(orderNo);
    }


}
