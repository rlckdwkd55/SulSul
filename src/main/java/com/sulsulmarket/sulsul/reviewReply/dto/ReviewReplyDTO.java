package com.sulsulmarket.sulsul.reviewReply.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ReviewReplyDTO {
    private int REPLY_ID ; // 고유 Index
    private int REVIEW_NO ; // 리뷰 번호
    private String RESPONDER_ID ; // 답글 다는 회원 || 관리자 아이디
    private String REPLY_CONTENT ; // 답글 내용
    private LocalDateTime CREATED_DATETIME ;
    private LocalDateTime UPDATE_DATETIME;}
