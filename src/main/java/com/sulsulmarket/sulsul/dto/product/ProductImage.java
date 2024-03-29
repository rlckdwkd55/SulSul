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
    @JsonProperty("productOldImage")
    private String PRODUCT_OLD_IMAGE;
    @JsonProperty("productNewImage")
    private String PRODUCT_NEW_IMAGE;
}