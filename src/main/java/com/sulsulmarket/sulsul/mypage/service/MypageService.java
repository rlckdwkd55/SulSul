package com.sulsulmarket.sulsul.mypage.service;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.mypage.ClaimInfo;
import com.sulsulmarket.sulsul.dto.order.OrderDetail;
import com.sulsulmarket.sulsul.dto.order.Orders;
import com.sulsulmarket.sulsul.dto.review.Review;
import com.sulsulmarket.sulsul.mypage.dao.MypageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MypageService {

    @Autowired
    private MypageDao mypageDao;

    public Map<String, Object> getOrderList(Map<String, String>  requestBody) throws Exception{
        List<Orders> orderList;
        Map<String, Object> resultMap = new HashMap<>();
        List<OrderDetail> detailList;
        String memberId = requestBody.get("memberId");
        if(memberId != null){
            orderList = mypageDao.getOrderList(memberId);
            if(orderList != null){
                resultMap.put("orderList", orderList);
                for(Orders order : orderList) {
                    if(order.getORDER_NO() != 0){
                        detailList = getOrderDetailList(order.getORDER_NO());
                        if(detailList != null) {
                            resultMap.put(String.valueOf(order.getORDER_NO()), detailList);
                        }
                    }
                }
            } else{
                log.warn("getOrderList is Null");
                return null;
            }
        } else{
            log.error("get Order List ,Member Id is Null : {}", memberId);
            return null;
        }
        return resultMap;
    }

    public List<OrderDetail> getOrderDetailList(int orderNo){
        List<OrderDetail> resultList = mypageDao.getOrderDetailList(orderNo);
        return resultList;
    }

    public List<Review> getReviewList(String memberId){

        List<Review> reviewList =  mypageDao.getReviewList(memberId);
        log.info("ReviewListResult : {} ", reviewList.toString());

        if (reviewList == null || reviewList.isEmpty()){
            throw new NullPointerException("가져올 리뷰 리스트가 없습니다.");
        }

        return reviewList;
    }

    public List<Member> getUserInfo(String memberId){

        List<Member> resultList = mypageDao.getUserInfo(memberId);
        log.info("UserInfoResult : {}", resultList);

        if (resultList == null || resultList.isEmpty()){
            throw new NullPointerException("유저 정보가 없습니다.");
        }

        return resultList;
    }

    public List<Address> getAddressList(String memberId){
        List<Address> resultList = mypageDao.getAddressList(memberId);
        log.info("AddressResult : {}", resultList);

        if (resultList == null || resultList.isEmpty()){
            throw new NullPointerException("주소지 정보가 없습니다.");
        }
        return resultList;
    }

    public List<ClaimInfo> getCancelRefundList(String memberId){
        List<ClaimInfo> resultList = mypageDao.getCancelRefundList(memberId);
        log.info("ClaimResult : {}", resultList);

        if (resultList == null || resultList.isEmpty()){
            throw new NullPointerException("주문 취소내역이 없습니다.");
        }
        return resultList;
    }
}
