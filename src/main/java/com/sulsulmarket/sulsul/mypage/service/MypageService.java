package com.sulsulmarket.sulsul.mypage.service;

import com.sulsulmarket.sulsul.mypage.dao.MypageDao;
import com.sulsulmarket.sulsul.mypage.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MypageService {

    @Autowired
    private MypageDao mypageDao;

    public List<Orders> getOrderList(Map<String, Object> parameter){
        List<Orders> resultList = mypageDao.getOrderList(parameter);
        return resultList;
    }

    public List<OrderDetail> getOrderDetailList(Map<String, Object> parameter){
        List<OrderDetail> resultList = mypageDao.getOrderDetailList(parameter);
        return resultList;
    }

    public List<Review> getReviewList(Map<String, String> parameter){
        List<Review> resultList = mypageDao.getReviewList(parameter);
        return resultList;
    }

    public List<Member> getUserInfo(Map<String, String> parameter){
        List<Member> resultList = mypageDao.getUserInfo(parameter);
        return resultList;
    }

    public List<Address> getAddressList(Map<String, String> parameter){
        List<Address> resultList = mypageDao.getAddressList(parameter);
        return resultList;
    }

    public List<ClaimInfo> getCancelRefundList(Map<String, String> parameter){
        List<ClaimInfo> resultList = mypageDao.getCancelRefundList(parameter);
        return resultList;
    }
}
