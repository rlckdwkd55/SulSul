package com.sulsulmarket.sulsul.dto.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ClaimInfo {
    private int CLAIM_NO; //클레임번호
    private int DETAIL_NO; //상세번호
    private String MEMBER_EMAIL; // 이메일 -
    private int CLAIM_CODE; //클레임코드 -
    private String CLAIM_CONTENT; //클레임사유
    private LocalDateTime CLAIM_DATE; //클레임날짜
    private String CLAIM_IMAGE; //클레임이미지
    private LocalDateTime COMPLETE_DATE; //클레임완료일
    private String CLAIM_NAME; // 클레임 드롭박스
}
