package com.sulsulmarket.sulsul.mypage.mapper;

import com.sulsulmarket.sulsul.mypage.dto.Orders;
import com.sulsulmarket.sulsul.mypage.dto.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MypageMapper {
    List<Orders> getOrderList(Map<String, String> parameter);

    List<Review> getReviewList(Map<String, String> parameter);
}
