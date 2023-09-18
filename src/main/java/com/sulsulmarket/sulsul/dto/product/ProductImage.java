package com.sulsulmarket.sulsul.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductImage {
    @JsonProperty("productNo")
    private int PRODUCT_NO;
    @JsonProperty("productImageNo")
    private int PRODUCT_IMAGE_NO;
    @JsonProperty("productImage")
    private String PRODUCT_IMAGE;
}