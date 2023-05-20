package com.sulsulmarket.sulsul.member.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDTO {

    private String MEMBER_ID;
    private String MEMBER_PW;
    private String MEMBER_NAME;
    private String MEMBER_BIRTHDATE;
    private String MEMBER_GENDER;
    private String MEMBER_ADDRESS;
    private String MEMBER_PHONE;
    private String MEMBER_EMAIL;
    private LocalDateTime MEMBER_ENROLLDATE;
    private String MEMBER_STATUS;
}
