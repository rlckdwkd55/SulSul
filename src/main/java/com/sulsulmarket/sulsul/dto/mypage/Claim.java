package com.sulsulmarket.sulsul.dto.mypage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Claim {
    @JsonProperty("claimCode")
    private int CLAIM_CODE; //클레임 코드
    @JsonProperty("claimName")
    private String CLAIM_NAME; //클레임 상태명
}
