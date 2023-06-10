package com.sulsulmarket.sulsul.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CartDTO {

    @JsonProperty("memberId")
    private String MEMBER_ID;
    @JsonProperty("productNo")
    private int PRODUCT_NO;
    @JsonProperty("cartAmount")
    private int CART_AMOUNT;

    public CartDTO toDTO(String memberId, int productNo, int carAmount) {
        this.MEMBER_ID = memberId;
        this.PRODUCT_NO = productNo;
        this.CART_AMOUNT = carAmount;

        return this;
    }

    public CartDTO toDTO(int productNo, int carAmount) {
        this.PRODUCT_NO = productNo;
        this.CART_AMOUNT = carAmount;

        return this;
    }
}
