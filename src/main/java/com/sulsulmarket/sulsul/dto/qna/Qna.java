package com.sulsulmarket.sulsul.dto.qna;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class Qna {

    @JsonProperty("qnaNo")
    private int QNA_NO;
    @JsonProperty("email")
    private String MEMBER_EMAIL;
    @JsonProperty("productNo")
    private int PRODUCT_NO;
    @JsonProperty("qnaTitle")
    private String QNA_TITLE;
    @JsonProperty("qnaContent")
    private String QNA_CONTENT;
    @JsonProperty("createdTime")
    private LocalDateTime CREATED_TIME;
    @JsonProperty("updatedTime")
    private LocalDateTime UPDATED_TIME;
    @JsonProperty("delStatus")
    private String DEL_STATUS;
}
