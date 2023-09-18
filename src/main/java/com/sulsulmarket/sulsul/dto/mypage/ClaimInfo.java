package com.sulsulmarket.sulsul.dto.mypage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ClaimInfo {
    @JsonProperty("claimNo")
    private int CLAIM_NO; //클레임번호
    @JsonProperty("detailNo")
    private int DETAIL_NO; //상세번호
    @JsonProperty("email")
    private String MEMBER_EMAIL; // 이메일 -
    @JsonProperty("claimCode")
    private int CLAIM_CODE; //클레임코드 -
    @JsonProperty("claimContent")
    private String CLAIM_CONTENT; //클레임사유
    @JsonProperty("claimDate")
    private LocalDateTime CLAIM_DATE; //클레임날짜
    @JsonProperty("claimImage")
    private String CLAIM_IMAGE; //클레임이미지
    @JsonProperty("completeDate")
    private LocalDateTime COMPLETE_DATE; //클레임완료일
    @JsonProperty("claimName")
    private String CLAIM_NAME; // 클레임 드롭박스
}
