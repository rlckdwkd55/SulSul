package com.sulsulmarket.sulsul.orders.mapper;

import com.sulsulmarket.sulsul.product.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OrdersMapper {

    int getInsertOrders(Map<String, Object> orderParameter);

    int getInsertOrderDetail(Map<String, Object> detailParameter);
}
