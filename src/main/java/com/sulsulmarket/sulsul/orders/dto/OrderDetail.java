package com.sulsulmarket.sulsul.orders.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetail {

    private int DETAIL_NO; //상세번호
    private int ORDER_NO; //주문번호
    private int PRODUCT_NO; //제품NO
    private int DELEVERY_NO; //배송송장번호
    private int PROCESS_CODE; //주문진행코드
    private int DETAIL_AMOUNT; //수량
    private int DETAIL_PRICE; //가격
    private String PAY_DATE; //결제일

}
