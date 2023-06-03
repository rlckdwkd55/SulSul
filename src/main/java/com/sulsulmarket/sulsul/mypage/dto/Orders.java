package com.sulsulmarket.sulsul.mypage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Orders {
    private int ORDER_NO; //주문번호
    private String MEMBER_ID; // 아이디
    private int TID; // 결제승인번호
    private int PAY_CODE; //결제코드
    private Date ORDER_DATE; //주문날짜
    private String ORDER_ADRESS; //배송지주소
    private String ORDER_RECEIVER; //수령인
    private String ORDER_REQUEST; //배송요청사항
    private String ORDER_PHONE; //수령인연락처

}
