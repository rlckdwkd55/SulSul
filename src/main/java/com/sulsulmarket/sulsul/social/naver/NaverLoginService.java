package com.sulsulmarket.sulsul.social.naver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
public class NaverLoginService {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String redirectUrl;

    public String getAuthorizationUrl() {
        return "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&response_type=code";
    }

    public String getTokenResponse(String code) {
        String tokenUrl = "https://nid.naver.com/oauth2.0/token";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUrl);
        params.add("code", code);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);
        String tokenResponse = response.getBody();
        log.info("[INFO] tokenResponse -> {}", tokenResponse);
        return tokenResponse;
    }

    public NaverUserDTO getUserInfo(String code) {
        String tokenResponse = getTokenResponse(code);
        log.info("tokenResponse : -> {}", tokenResponse);

        // 토큰 응답을 파싱하여 필요한 정보 추출
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(tokenResponse);
            String accessToken = jsonNode.get("access_token").asText();
            log.info("accessToken -> {}", accessToken);

            // 토큰을 사용하여 회원 정보를 가져오는 API 호출
            String userInfoUrl = "https://openapi.naver.com/v1/nid/me";
            HttpHeaders userInfoHeaders = new HttpHeaders();
            userInfoHeaders.set("Authorization", "Bearer " + accessToken);
            HttpEntity<String> userInfoRequestEntity = new HttpEntity<>(userInfoHeaders);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequestEntity, String.class);
            log.info("userInfoResponse -> {}", userInfoResponse);
            String userInfo = null;
            if (userInfoResponse.getStatusCode() == HttpStatus.PERMANENT_REDIRECT) {
                String redirectUrl = userInfoResponse.getHeaders().getLocation().toString();
                ResponseEntity<String> redirectResponse = restTemplate.exchange(redirectUrl, HttpMethod.GET, userInfoRequestEntity, String.class);
                userInfo = redirectResponse.getBody();
            } else {
                userInfo = userInfoResponse.getBody();
            }

            log.info("[INFO] userInfo -> {}", userInfo);

            // 회원 정보를 파싱하여 NaverUserDTO 객체로 반환
            NaverUserDTO userInfoObj = objectMapper.readValue(userInfo, NaverUserDTO.class);
            return userInfoObj;
        } catch (IOException e) {
            log.error("[ERROR] Failed to parse token response or user info", e);
            throw new IllegalStateException("Failed to parse token response or user info", e);
        }
    }
}
