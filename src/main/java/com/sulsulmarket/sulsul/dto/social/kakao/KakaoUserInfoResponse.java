package com.sulsulmarket.sulsul.dto.social.kakao;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class KakaoUserInfoResponse {
    private Long id;
    private String connected_at;
    private Map<String, String> properties;
    private KakaoAccount kakao_account;
}
