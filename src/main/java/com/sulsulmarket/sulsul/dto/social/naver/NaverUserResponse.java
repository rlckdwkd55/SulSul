package com.sulsulmarket.sulsul.dto.social.naver;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NaverUserResponse {
    private String id;
    private String gender;
    private String email;
    private String mobile;
    private String mobile_e164;
    private String name;
    private String birthday;
    private String birthyear;
}
