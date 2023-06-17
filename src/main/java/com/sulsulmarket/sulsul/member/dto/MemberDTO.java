package com.sulsulmarket.sulsul.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDTO {

    @JsonProperty("memberId")
    @NotNull(message = "아이디는 필수 입력 값")
    private String MEMBER_ID;

    @JsonProperty("password")
    @NotNull(message = "비밀번호는 필수 입력 값")
    private String MEMBER_PW;

    @JsonProperty("name")
    @NotNull(message = "이름은 필수 입력 값")
    private String MEMBER_NAME;

    @JsonProperty("birthdate")
    @NotNull(message = "생년월일 필수 입력 값")
    private String MEMBER_BIRTHDATE;

    @JsonProperty("gender")
    private String MEMBER_GENDER;

    @JsonProperty("address")
    @NotNull(message = "주소는 필수 입력 값")
    private String MEMBER_ADDRESS;

    @JsonProperty("phone")
    @NotNull(message = "전화번호는 필수 입력 값")
    private String MEMBER_PHONE;

    @JsonProperty("email")
    @NotNull(message = "이메일은 필수 입력 값")
    private String MEMBER_EMAIL;

    private LocalDateTime MEMBER_ENROLLDATE;

    private String MEMBER_ROLE;
}