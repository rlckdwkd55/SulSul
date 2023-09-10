package com.sulsulmarket.sulsul.dto.social.kakao;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoTokenResponse {

    private String access_token;
    private String refresh_token;
    private Integer expires_in;
    private Integer refresh_token_expires_in;
}
