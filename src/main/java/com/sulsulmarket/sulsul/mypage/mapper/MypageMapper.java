package com.sulsulmarket.sulsul.mypage.mapper;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.mypage.ClaimInfo;
import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import com.sulsulmarket.sulsul.dto.order.Orders;
import com.sulsulmarket.sulsul.dto.review.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MypageMapper {
    List<Orders> getOrderList(Map<String, Object> parameter);

    List<OrderDetail> getOrderDetailList(Map<String, Object> parameter);

    List<Review> getReviewList(Map<String, String> parameter);

    List<Member> getUserInfo(Map<String, String> parameter);

    List<Address> getAddressList(Map<String, String> parameter);

    List<ClaimInfo> getCancelRefundList(Map<String, String> parameter);
}
