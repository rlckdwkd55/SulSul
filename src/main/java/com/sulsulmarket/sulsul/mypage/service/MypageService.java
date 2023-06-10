package com.sulsulmarket.sulsul.mypage.service;

import com.sulsulmarket.sulsul.mypage.dao.MypageDao;
import com.sulsulmarket.sulsul.mypage.dto.Orders;
import com.sulsulmarket.sulsul.mypage.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MypageService {

    @Autowired
    private MypageDao mypageDao;

    public List<Orders> getOrderList(Map<String, String> parameter){
        List<Orders> resultList = mypageDao.getOrderList(parameter);
        return resultList;
    }

    public List<Review> getReviewList(Map<String, String> parameter){
        List<Review> resultList = mypageDao.getReviewList(parameter);
        return resultList;
    }
}
