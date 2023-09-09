package com.sulsulmarket.sulsul.dto.social.naver;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
/**
 * Naver Token Response
 */
public class NaverTokenResponse {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private Integer expires_in;
    private String error;
    private String error_description;
}
