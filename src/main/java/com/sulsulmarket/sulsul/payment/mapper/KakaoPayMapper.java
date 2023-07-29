package com.sulsulmarket.sulsul.payment.mapper;

import com.sulsulmarket.sulsul.payment.dto.OrderDetail;
import com.sulsulmarket.sulsul.orders.dto.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KakaoPayMapper{

    void putTid(String tid, String orderNo);


    List<OrderDetail> getOrderDetailData(int orderNo);

    void cancelTid(String tid, String orderNo);
}