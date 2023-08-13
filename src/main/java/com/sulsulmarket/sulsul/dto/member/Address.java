package com.sulsulmarket.sulsul.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {

    @JsonProperty("memberId")
    private String MEMBER_ID; //아이디
    @JsonProperty("address")
    private String ADDRESS; //배송지
}
