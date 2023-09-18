package com.sulsulmarket.sulsul.dto.member;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignRequestMember {
    private String name;
    private String birthDay;
    private String email;
    private String mobile;
    private String gender;
    private Address address;
}
