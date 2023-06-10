package com.sulsulmarket.sulsul.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sulsulmarket.sulsul.ennumurate.MemberRoleType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDTO {

    @JsonProperty("memberId")
//    @NotNull(message = "아이디는 필수 입력 값")
    private String MEMBER_ID;

    @JsonProperty("password")
//    @NotNull(message = "비밀번호는 필수 입력 값")
    private String MEMBER_PW;

    @JsonProperty("memberName")
    private String MEMBER_NAME;

    @JsonProperty("memberBirthDate")
    private String MEMBER_BIRTHDATE;

    @JsonProperty("memberGender")
//    @NotNull(message = "성별은 필수 입력 값")
    private String MEMBER_GENDER;

    @JsonProperty("memberAddress")
//    @NotNull(message = "주소는 필수 입력 값")
    private String MEMBER_ADDRESS;

    @JsonProperty("memberPhone")
//    @NotNull(message = "전화번호는 필수 입력 값")
    private String MEMBER_PHONE;

    @JsonProperty("memberEmail")
//    @NotNull(message = "이메일은 필수 입력 값")
    private String MEMBER_EMAIL;

    @JsonProperty("memberEnrollDate")
    private LocalDateTime MEMBER_ENROLLDATE;

    @JsonProperty("memberRole")
    private MemberRoleType MEMBER_ROLE;
}