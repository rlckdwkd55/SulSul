package com.sulsulmarket.sulsul.mypage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Claim {
    private int CLAIM_CODE; //클레임 코드
    private String CLAIM_NAME; //클레임 상태명
}
