package com.sulsulmarket.sulsul.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sulsulmarket.sulsul.main.dto.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class CartDTO {

    @JsonProperty("memberId")
    private String MEMBER_ID;
    @JsonProperty("productNo")
    private int PRODUCT_NO;
    @JsonProperty("productList")
    private HashMap<Integer, ProductJoinDTO> param;
    @JsonProperty("cartAmount")
    private int CART_AMOUNT;
}
