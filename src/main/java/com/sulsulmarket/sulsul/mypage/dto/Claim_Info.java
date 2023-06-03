package com.sulsulmarket.sulsul.mypage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Claim_Info {
    private int CLAIM_NO; //클레임번호
    private int DETAIL_NO; //상세번호
    private int CLAIM_CODE; //클레임코드
    private int PAYBACK_CODE; //환불코드
    private String CLAIM_CONTENT; //클레임사유
    private LocalDateTime CLAIM_DATE; //클레임날짜
    private String CLAIM_IMAGE; //클레임이미지
    private LocalDateTime COMPLETE_DATE; //클레임완료일
}
