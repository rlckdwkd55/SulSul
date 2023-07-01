package com.sulsulmarket.sulsul.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoPayRequest {
    private int productNo; //상품번호
    private int quantity; //수량
    private int totalAmount; //총 금액
    private int taxFreeAmount; //비과세금액
}
