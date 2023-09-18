package com.sulsulmarket.sulsul.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
/**
 * Member Table DTO Class
 */
public class Member {

    @JsonProperty("name")
    private String MEMBER_NAME;

    @JsonProperty("birthdate")
    private String MEMBER_BIRTHDATE;

    @JsonProperty("gender")
    private String MEMBER_GENDER;

    @JsonProperty("address")
    @Setter
    private Address address;

    @JsonProperty("phone")
    private String MEMBER_PHONE;

    @JsonProperty("email")
    private String MEMBER_EMAIL;

    private LocalDateTime REG_TIME;

    private String MEMBER_ROLE;

}