package com.sulsulmarket.sulsul.mypage.dao;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.mypage.ClaimInfo;
import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import com.sulsulmarket.sulsul.dto.order.Orders;
import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.mypage.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MypageDao implements MypageMapper{

    @Autowired
    private MypageMapper mypageMapper;

    @Override
    public List<Orders> getOrderList(String memberId) {
        return mypageMapper.getOrderList(memberId);
    }

    @Override
    public List<OrderDetail> getOrderDetailList(int orderNo) {
        return mypageMapper.getOrderDetailList(orderNo);
    }

    @Override
    public List<Review> getReviewList(String memberId) {

        return mypageMapper.getReviewList(memberId);
    }

    @Override
    public List<Member> getUserInfo(String memberId) {
        return mypageMapper.getUserInfo(memberId);
    }

    @Override
    public List<Address> getAddressList(String memberId) {
        return mypageMapper.getAddressList(memberId);
    }

    @Override
    public List<ClaimInfo> getCancelRefundList(String memberId) {
        return mypageMapper.getCancelRefundList(memberId);
    }


}
