package com.sulsulmarket.sulsul.dto.naver;

import com.sulsulmarket.sulsul.social.naver.NaverUserResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NaverUser {
    private String resultCode;
    private String message;
    private NaverUserResponse response;
}
