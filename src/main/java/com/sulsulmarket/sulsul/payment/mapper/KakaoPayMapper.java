package com.sulsulmarket.sulsul.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoPayMapper{

    void putTid(String tid, String orderNo);

    void cancelTid(String tid, String orderNo);

}