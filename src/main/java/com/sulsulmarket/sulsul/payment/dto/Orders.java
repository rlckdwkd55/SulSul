package com.sulsulmarket.sulsul.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Orders {

    private int ORDER_NO; //주문번호
    private String MEMBER_ID; //아이디
    private int TID; //결제승인번호
    private int PAY_CODE; //결제코드
    private String ORDER_DATE; //주문날짜
    private String ORDER_ADDRESS; //배송지주소
    private String ORDER_RECEIVER; //수령인
    private String REQUEST; //배송요청사항
    private String ORDER_PHONE; //수령인연락처
}
