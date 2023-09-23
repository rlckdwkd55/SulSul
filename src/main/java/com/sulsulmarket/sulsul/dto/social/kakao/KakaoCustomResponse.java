package com.sulsulmarket.sulsul.dto.social.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoCustomResponse {
    private KakaoUserInfoResponse kakaoUserInfoResponse;
    private String resultcode;

    @Builder
    public KakaoCustomResponse(KakaoUserInfoResponse kakaoUserInfoResponse, String resultcode) {
        this.kakaoUserInfoResponse = kakaoUserInfoResponse;
        this.resultcode = resultcode;
    }
}
