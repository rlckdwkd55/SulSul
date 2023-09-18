package com.sulsulmarket.sulsul.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {

    @JsonProperty("email")
    private String MEMBER_EMAIL; //아이디
    @JsonProperty("address")
    private String ADDRESS; //배송지
    @JsonProperty("detailAddress")
    private String DETAIL_ADDRESS; // 상세 주소
    @JsonProperty("postNo")
    private String POST_NO; // 우편 번호
}
