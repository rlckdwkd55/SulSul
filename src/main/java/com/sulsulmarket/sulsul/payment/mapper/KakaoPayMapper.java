package com.sulsulmarket.sulsul.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface KakaoPayMapper{
    String getResponsePayment(Map<String, Object> paymentMap);

    // ORDER_NO, MEMBER_ID, TID, AID, ORDER_DATE, ORDER_ADDRESS, ORDER_RECEIVER, ORDER_REQUEST, ORDER_PHONE, DETAIL_NO, PRODUCT_NO
//    public void approveInsert(String pgToken, int tid);
}