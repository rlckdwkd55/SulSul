package com.sulsulmarket.sulsul.payment.dao;

import com.sulsulmarket.sulsul.payment.dto.KakaoApproveResponse;
import com.sulsulmarket.sulsul.payment.mapper.KakaoPayMapper;
import com.sulsulmarket.sulsul.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KakaoPayDao {

    @Autowired
    private KakaoPayMapper kakaoPayMapper;

//    @Override
//    public void approveResponse(String pgToken, int tid) {
//        return kakaoPayMapper.approveInsert(pgToken, tid);
//    }
}
