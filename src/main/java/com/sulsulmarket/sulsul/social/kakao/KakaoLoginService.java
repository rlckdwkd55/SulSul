






package com.sulsulmarket.sulsul.social.kakao;

import com.google.gson.Gson;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoAuthDTO;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoTokenResponse;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoUserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KakaoLoginService {

    private String url = "https://kauth.kakao.com/oauth/authorize";
    @Autowired
    private KakaoAuthDTO kakaoAuth;

    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    public String getRedirectUrl() {
        log.info("kakao auth Check : [{}]", kakaoAuth);

        String requestUrl = url + "?response_type=code" + "&client_id=" + kakaoAuth.getClientId() + "&redirect_uri=" + kakaoAuth.getRedirectUri();
        log.info("Request Url Check : {}", requestUrl);
        ResponseEntity response = restTemplate.getForEntity(requestUrl, Object.class);
        log.info("Response Check : {}, ''''''''''''''''''''{}", response.getBody(), response);
        return requestUrl;
    }

    public String tokenRequest(String code) {
        log.info("kakao Token : [{}], Code : [{}]", kakaoAuth.getTokenUri(), code);
        try {
            String tokenRequestUrl = kakaoAuth.getTokenUri() + "?grant_type=authorization_code" + "&client_id=" + kakaoAuth.getClientId() +
                    "&client_secret=" + kakaoAuth.getClientSecret() + "&redirect_uri=" + kakaoAuth.getRedirectUri() + "&code=" + code;

            ResponseEntity<String> response = restTemplate.getForEntity(tokenRequestUrl, String.class);
            log.info("response Check {}, {}", response.getBody(), response);
            KakaoTokenResponse tokenResponse = gson.fromJson(response.getBody(), KakaoTokenResponse.class);
            log.info("Token Response Check .. : [{}]", tokenResponse);
            return tokenResponse.getAccess_token();
        } catch (Exception e) {
            log.error("Token Request Exception.", e);
            return null;
        }
    }

    public KakaoUserInfoResponse getUserInfo(String code) {

        String token = tokenRequest(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(kakaoAuth.getUserInfoUri(), HttpMethod.GET, entity, String.class);
        log.info("Response Check : {}", response.getBody());
        KakaoUserInfoResponse userInfoResponse = gson.fromJson(response.getBody(), KakaoUserInfoResponse.class);
        log.info("Check man,.. :: [{}]", userInfoResponse);
        return userInfoResponse;
    }
}