package com.sulsulmarket.sulsul.mypage.service;

import com.sulsulmarket.sulsul.mypage.dao.MypageDao;
import com.sulsulmarket.sulsul.mypage.dto.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MypageService {

    @Autowired
    private MypageDao mypageDao;

    public Orders getOrderList(Map<String, Integer> parameter){

        return null;
    }
}
