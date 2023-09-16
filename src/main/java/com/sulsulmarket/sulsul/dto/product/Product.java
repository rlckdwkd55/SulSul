package com.sulsulmarket.sulsul.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Product {
    @JsonProperty("productNo")
    private Integer PRODUCT_NO; // 제품NO
    @JsonProperty("categoryNo")
    private Integer CATEGORY_NO; // 카테고리 번호
    @JsonProperty("productName")
    private String PRODUCT_NAME; // 제품이름
    @JsonProperty("productPrice")
    private Integer PRODUCT_PRICE; // 제품가격
    @JsonProperty("productAmount")
    private Integer PRODUCT_AMOUNT; // 재고
    @JsonProperty("productStatus")
    private String PRODUCT_STATUS; // 삭제유무
    @JsonProperty("productInfo")
    private String PRODUCT_INFO; // 제품 상세설명
    @JsonProperty("productCount")
    private Integer PRODUCT_COUNT; // 상품조회수
    @JsonProperty("paymentCount")
    private Integer PAYMENT_COUNT; // 상품주문횟수
    @JsonProperty("uploadDate")
    private Date UPLOAD_DATE; // 상품등록일
    @JsonProperty("productImage")
    private ProductImage PRODUCT_IMAGE; //상품이미지
    @JsonProperty("productAlcohol")
    private String PRODUCT_ALCOHOL;
    @JsonProperty("productCapacity")
    private String PRODUCT_CAPACITY;
}
