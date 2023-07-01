package com.sulsulmarket.sulsul.orders.service;

import com.sulsulmarket.sulsul.Util.SulSulUil;
import com.sulsulmarket.sulsul.orders.dao.OrdersDao;
import com.sulsulmarket.sulsul.orders.dto.Member;
import com.sulsulmarket.sulsul.orders.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
@Slf4j
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    public int insertOrders(Map<String, Object> requestOrder){

        int result = 0;

        String memberId = (String)requestOrder.get("memberId");
        Date orderDate = SulSulUil.getCurrentTime();
        String orderAddress = (String)requestOrder.get("orderAddress");
        String orderReceiver =(String) requestOrder.get("orderReceiver");
        String orderRequest = (String)requestOrder.get("orderRequest");
        String orderPhone = (String)requestOrder.get("orderPhone");
        List<Product> orderDetailList = (List<Product>)requestOrder.get("orderDetail");

        Map<String, Object> orderParameter = new HashMap<>();

        int orderNo = Math.toIntExact(SulSulUil.getNextSequence());
        // TODO 생성된 orderNo 값을 가지고 orders table에서 조회


        orderParameter.put("ORDER_NO", orderNo);
        orderParameter.put("MEMBER_ID", memberId);
        orderParameter.put("ORDER_DATE", String.valueOf(orderDate));
        orderParameter.put("ORDER_ADDRESS", orderAddress);
        orderParameter.put("ORDER_RECEIVER", orderReceiver);
        orderParameter.put("MEMBER_REQUEST", orderRequest);
        orderParameter.put("MEMBER_PHONE", orderPhone);
        //orders 테이블 insert하고

        int ordersResult = ordersDao.insertOrders(orderParameter);


        if(ordersResult == 1){
            if(orderDetailList.size() > 0) {
                for(int i = 0; i < orderDetailList.size(); i++) {
                    Product product = orderDetailList.get(i);
                    Map<String,Object> detailParameter = new HashMap<>();
                    detailParameter.put("DETAIL_NO", i);
                    detailParameter.put("ORDER_NO",);
                    detailParameter.put("PRODUCT_NO",);
                    detailParameter.put("DELIVERY_NO",);
                    detailParameter.put("PROCESS_CODE",);
                    detailParameter.put("DETAIL_AMOUNT",);
                    detailParameter.put("DETAIL_PRICE",);
                    detailParameter.put("PAY_DATE",);
                    int orderDetailResult = ordersDao.insertOrderDetail(detailParameter);
                    if(orderDetailResult == 1){

                    } else{
                        //orderDetail Insert도 롤백
                        //orders insert도 롤백
                        //그리고 return은 실패
                        return 0;
                    }
                }

                for(Product product : orderDetailList) {
                    Map<String,Object> detailParameter = new HashMap<>();
                    int idx = orderDetailList.indexOf(product);
                    detailParameter.put("DETAIL_NO", idx);

                    int orderDetailResult = ordersDao.insertOrderDetail(detailParameter);
                    if(orderDetailResult == 1){

                    } else{
                        //orderDetail Insert도 롤백
                        //orders insert도 롤백
                        //그리고 return은 실패

                        return 0;

                    }
                }
                result = 1;
            }

        } else{
            log.error("Orders Insert Failed ...");
        }




        //memberId로 memeber 객체 받아와 아래꺼 구현해줭
        Member member = ordersDao.getMember(memberId);

        return result;
    }

}
