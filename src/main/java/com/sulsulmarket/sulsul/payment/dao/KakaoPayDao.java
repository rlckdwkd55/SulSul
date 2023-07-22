package com.sulsulmarket.sulsul.payment.dao;

import com.sulsulmarket.sulsul.payment.dto.KakaoApproveResponse;
import com.sulsulmarket.sulsul.payment.mapper.KakaoPayMapper;
import com.sulsulmarket.sulsul.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Component
public class KakaoPayDao implements KakaoPayMapper{

    @Autowired
    private KakaoPayMapper kakaoPayMapper;

    @Override
    public String getResponsePayment(@RequestBody Map<String, Object> paymentMap){
        return kakaoPayMapper.getResponsePayment(paymentMap);
    }

}
