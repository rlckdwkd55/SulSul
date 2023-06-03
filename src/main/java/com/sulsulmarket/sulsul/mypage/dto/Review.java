package com.sulsulmarket.sulsul.mypage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Review {
    private int REVIEW_NO; //리뷰번호
    private String MEMBER_ID; //아이디
    private int DETAIL_NO; //상세번호
    private String REVIEW_CONTENT; //리뷰내용
    private int REVIEW_SCORE; //리뷰점수
    private int REVIEW_DATE; //작성날짜
    private String REVIEW_STATUS; //삭제여부
    private String REVIEW_OLDIMAGE; //리뷰이미지_기존
    private String REVIEW_NEWIMAGE; //리뷰이미지_수정
}
