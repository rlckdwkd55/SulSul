package com.sulsulmarket.sulsul.mypage.dao;

import com.sulsulmarket.sulsul.mypage.dto.Orders;
import com.sulsulmarket.sulsul.mypage.dto.Review;
import com.sulsulmarket.sulsul.mypage.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MypageDao implements MypageMapper{

    @Autowired
    private MypageMapper mypageMapper;

    @Override
    public List<Orders> getOrderList(Map<String, String> parameter) {
        return mypageMapper.getOrderList(parameter);
    }

    @Override
    public List<Review> getReviewList(Map<String, String> parameter) {
        return mypageMapper.getReviewList(parameter);
    }
}
