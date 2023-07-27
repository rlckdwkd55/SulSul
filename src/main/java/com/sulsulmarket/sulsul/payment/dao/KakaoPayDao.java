package com.sulsulmarket.sulsul.payment.dao;

import com.sulsulmarket.sulsul.payment.mapper.KakaoPayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KakaoPayDao implements KakaoPayMapper{

    @Autowired
    private KakaoPayMapper kakaoPayMapper;

    @Override
    public void putTid(String tid, String orderNo){

        kakaoPayMapper.putTid(tid, orderNo);
    }

    @Override
    public void cancelTid(String tid, String orderNo){
        kakaoPayMapper.cancelTid(tid, orderNo);
    }

}
