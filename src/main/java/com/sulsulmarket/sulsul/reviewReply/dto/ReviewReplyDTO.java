package com.sulsulmarket.sulsul.reviewReply.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class ReviewReplyDTO {
    @JsonProperty("replyId")
    private int REPLY_ID ; // 고유 Index
    @JsonProperty("reviewNo")
    private int REVIEW_NO ; // 리뷰 번호
    @JsonProperty("responseId")
    private String RESPONDER_ID ; // 답글 다는 회원 || 관리자 아이디
    @JsonProperty("replyContent")
    private String REPLY_CONTENT ; // 답글 내용
    @JsonProperty("createdDateTime")
    private LocalDateTime CREATED_DATETIME;
    @JsonProperty("updatedDateTime")
    private LocalDateTime UPDATED_DATETIME;

    public int getREPLY_ID() {
        return REPLY_ID;
    }

    public void setREPLY_ID(int REPLY_ID) {
        this.REPLY_ID = REPLY_ID;
    }

    public int getREVIEW_NO() {
        return REVIEW_NO;
    }

    public void setREVIEW_NO(int REVIEW_NO) {
        this.REVIEW_NO = REVIEW_NO;
    }

    public String getRESPONDER_ID() {
        return RESPONDER_ID;
    }

    public void setRESPONDER_ID(String RESPONDER_ID) {
        this.RESPONDER_ID = RESPONDER_ID;
    }

    public String getREPLY_CONTENT() {
        return REPLY_CONTENT;
    }

    public void setREPLY_CONTENT(String REPLY_CONTENT) {
        this.REPLY_CONTENT = REPLY_CONTENT;
    }

    public LocalDateTime getCREATED_DATETIME() {
        return CREATED_DATETIME;
    }

    public void setCREATED_DATETIME(LocalDateTime CREATED_DATETIME) {
        this.CREATED_DATETIME = CREATED_DATETIME;
    }

    public LocalDateTime getUPDATED_DATETIME() {
        return UPDATED_DATETIME;
    }

    public void setUPDATED_DATETIME(LocalDateTime UPDATED_DATETIME) {
        this.UPDATED_DATETIME = UPDATED_DATETIME;
    }
}
