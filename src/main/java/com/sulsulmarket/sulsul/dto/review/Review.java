package com.sulsulmarket.sulsul.dto.review;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class Review {

    @JsonProperty("reviewNo")
    private int REVIEW_NO;
    @JsonProperty("memberId")
    private String MEMBER_ID;
    @JsonProperty("detailNo")
    private int DETAIL_NO;
    @JsonProperty("reviewContent")
    private String REVIEW_CONTENT;
    @JsonProperty("reviewScore")
    private int REVIEW_SCORE;
    @JsonProperty("reviewDate")
    private LocalDateTime REVIEW_DATE;
    @JsonProperty("reviewStatus")
    private String REVIEW_STATUS;
    @JsonProperty("reviewImage")
    private String REVIEW_IMAGE;
    @JsonProperty("updateTime")
    private LocalDateTime UPDATE_TIME;
}
