package com.sulsulmarket.sulsul.dto.social.naver;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverUser {
    private String resultcode;
    private String message;
    private NaverUserResponse response;
}
