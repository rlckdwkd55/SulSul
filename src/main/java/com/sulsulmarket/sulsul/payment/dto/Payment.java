package com.sulsulmarket.sulsul.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Payment {

    private int PAY_CODE; //결제코드
    private String PAY_NAME; //결제수단
    private String PAY_CATEGORY; //결제분류
}
