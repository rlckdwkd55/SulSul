package com.sulsulmarket.sulsul.review.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewDTO {

    private int REVIEW_NO;
    private String MEMBER_ID;
    private int DETAIL_NO;
    private String REVIEW_CONTENT;
    private int REVIEW_SCORE;
    private LocalDateTime REVIEW_DATE;
    private String REVIEW_STATUS;
    private String REVIEW_OLDIMAGE;
    private String REVIEW_NEWIMAGE;

    public int getREVIEW_NO() {
        return REVIEW_NO;
    }

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public int getDETAIL_NO() {
        return DETAIL_NO;
    }

    public String getREVIEW_CONTENT() {
        return REVIEW_CONTENT;
    }

    public int getREVIEW_SCORE() {
        return REVIEW_SCORE;
    }

    public LocalDateTime getREVIEW_DATE() {
        return REVIEW_DATE;
    }

    public String getREVIEW_STATUS() {
        return REVIEW_STATUS;
    }

    public String getREVIEW_OLDIMAGE() {
        return REVIEW_OLDIMAGE;
    }

    public String getREVIEW_NEWIMAGE() {
        return REVIEW_NEWIMAGE;
    }
}
