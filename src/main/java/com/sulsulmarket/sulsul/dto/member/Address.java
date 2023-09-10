package com.sulsulmarket.sulsul.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {

    private String MEMBER_EMAIL; //아이디
    private String ADDRESS; //배송지
    private String DETAIL_ADDRESS; // 상세 주소
    private String POST_NO; // 우편 번호
}
