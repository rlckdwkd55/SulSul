package com.sulsulmarket.sulsul.mypage.mapper;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.mypage.ClaimInfo;
import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import com.sulsulmarket.sulsul.dto.order.Orders;
import com.sulsulmarket.sulsul.dto.review.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {
    List<Orders> getOrderList(String memberEmail);

    List<OrderDetail> getOrderDetailList(int orderNo);

    List<Review> getReviewList(String memberEmail);

    List<Member> getUserInfo(String memberEmail);

    List<Address> getAddressList(String memberEmail);

    List<ClaimInfo> getCancelRefundList(String memberEmail);
}
