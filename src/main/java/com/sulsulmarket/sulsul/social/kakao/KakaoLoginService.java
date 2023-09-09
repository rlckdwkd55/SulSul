






package com.sulsulmarket.sulsul.social.kakao;

import com.sulsulmarket.sulsul.dto.social.kakao.KakaoAuth;
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
    private KakaoAuth kakaoAuth;
    private RestTemplate restTemplate = new RestTemplate();

    public String getRedirectUrl() {
        log.info("kakao auth Check : [{}]", kakaoAuth);

        String requestUrl = url + "?response_type=code" + "&client_id=" + kakaoAuth.getClientId() + "&redirect_uri=" + kakaoAuth.getRedirectUri();
        log.info("Request Url Check : {}", requestUrl);
        ResponseEntity response = restTemplate.getForEntity(requestUrl, Object.class);
        log.info("Response Check : {}, ''''''''''''''''''''{}", response.getBody(), response);
        return requestUrl;
    }

    public void tokenRequest(String code) {
        log.info("kakao Token : [{}], Code : [{}]", kakaoAuth.getTokenUri(), code);
        try {
            String tokenRequestUrl = kakaoAuth.getTokenUri() + "?grant_type=authorization_code" + "&client_id=" + kakaoAuth.getClientId() +
                    "&client_secret=" + kakaoAuth.getClientSecret() + "&redirect_uri=" + kakaoAuth.getRedirectUri() + "&code=" + code;

            ResponseEntity response = restTemplate.getForEntity(tokenRequestUrl, Object.class);
            log.info("response Check {}, {}", response.getBody(), response);
        } catch (Exception e) {
            log.error("Token Request Exception.", e);
        }
    }

    public void getUserInfo(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(kakaoAuth.getUserInfoUri(), HttpMethod.GET, entity, Object.class);
        log.info("Response Check : {}", response);
    }
}