package com.sulsulmarket.sulsul.orders.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Product {
    private Integer PRODUCT_NO; // 제품NO
    private Integer CATEGORY_NO; // 카테고리 번호
    private String PRODUCT_NAME; // 제품이름
    private Integer PRODUCT_PRICE; // 제품가격
    private Integer PRODUCT_AMOUNT; // 재고
    private String PRODUCT_STATUS; // 삭제유무
    private String PRODUCT_INFO; // 제품 상세설명
    private Integer PRODUCT_COUNT; // 상품조회수
    private Integer PAYMENT_COUNT; // 상품주문횟수
    private Date UPLOAD_DATE; // 상품등록일
}
