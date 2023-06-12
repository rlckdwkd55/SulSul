package com.sulsulmarket.sulsul.mypage.dao;

import com.sulsulmarket.sulsul.mypage.dto.*;
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
    public List<Orders> getOrderList(Map<String, Object> parameter) {
        return mypageMapper.getOrderList(parameter);
    }

    @Override
    public List<Order_Detail> getOrderDetailList(Map<String, Object> parameter) {
        return mypageMapper.getOrderDetailList(parameter);
    }

    @Override
    public List<Review> getReviewList(Map<String, String> parameter) {
        return mypageMapper.getReviewList(parameter);
    }

    @Override
    public List<Member> getUserInfo(Map<String, String> parameter) {
        return mypageMapper.getUserInfo(parameter);
    }

    @Override
    public List<Address> getAddressList(Map<String, String> parameter) {
        return mypageMapper.getAddressList(parameter);
    }

    @Override
    public List<Claim_Info> getCancelRefundList(Map<String, String> parameter) {
        return mypageMapper.getCancelRefundList(parameter);
    }


}
