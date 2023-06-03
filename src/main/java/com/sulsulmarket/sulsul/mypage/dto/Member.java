package com.sulsulmarket.sulsul.mypage.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Member {

    private String MEMBER_ID; //아이디
    private String MEMBER_PW; //비밀번호
    private String MEMBER_NAME; //이름
    private String MEMBER_BIRTHDATE; //생년월일
    private String MEMBER_GENDER; //성별
    private String MEMBER_ADDRESS; //주소
    private String MEMBER_PHONE; //연락처
    private String MEMBER_EMAIL; //이메일
    private LocalDateTime MEMBER_ENROLLDATE; //가입일
    private String MEMBER_STATUS; //탈퇴여부
}
