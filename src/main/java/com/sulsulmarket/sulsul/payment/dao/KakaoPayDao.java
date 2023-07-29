package com.sulsulmarket.sulsul.payment.dao;

import com.sulsulmarket.sulsul.payment.dto.OrderDetail;
import com.sulsulmarket.sulsul.orders.dto.Orders;
import com.sulsulmarket.sulsul.payment.mapper.KakaoPayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KakaoPayDao implements KakaoPayMapper{

    @Autowired
    private KakaoPayMapper kakaoPayMapper;

    @Override
    public void putTid(String tid, String orderNo){

        kakaoPayMapper.putTid(tid, orderNo);
    }

    @Override
    public List<OrderDetail> getOrderDetailData(int orderNo){

        return kakaoPayMapper.getOrderDetailData(orderNo);
    }

    @Override
    public void cancelTid(String tid, String orderNo){
        kakaoPayMapper.cancelTid(tid, orderNo);
    }

}
