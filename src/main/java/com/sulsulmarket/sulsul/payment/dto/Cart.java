package com.sulsulmarket.sulsul.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cart {

    private String MEMBER_ID; //아이디
    private int PRODUCT_NO; //제품NO
    private int CART_AMOUNT; //수량
}
