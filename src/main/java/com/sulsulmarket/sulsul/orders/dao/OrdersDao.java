package com.sulsulmarket.sulsul.orders.dao;

import com.sulsulmarket.sulsul.dto.order.Orders;
import com.sulsulmarket.sulsul.orders.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class OrdersDao implements OrdersMapper {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int getInsertOrders(Map<String, Object> orderParameter) {
        return ordersMapper.getInsertOrders(orderParameter);
    }

    @Override
    public int getInsertOrderDetail(Map<String, Object> detailParameter) {
        return ordersMapper.getInsertOrderDetail(detailParameter);
    }

    @Override
    public Orders getOrderData(int orderNo){
        return ordersMapper.getOrderData(orderNo);
    }
}
