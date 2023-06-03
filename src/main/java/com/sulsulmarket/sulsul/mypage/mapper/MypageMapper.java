package com.sulsulmarket.sulsul.mypage.mapper;

import com.sulsulmarket.sulsul.mypage.dto.Orders;
import com.sulsulmarket.sulsul.product.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MypageMapper {

    Orders getOrderList(Map<String, Integer> parameter);
}
