package com.sulsulmarket.sulsul.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sulsulmarket.sulsul.dto.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cart {

    @JsonProperty("memberId")
    private String MEMBER_ID;
    @JsonProperty("productNo")
    private int PRODUCT_NO;
    @JsonProperty("cartAmount")
    private int CART_AMOUNT;
    @JsonProperty("product")
    private Product PRODUCT;
}
