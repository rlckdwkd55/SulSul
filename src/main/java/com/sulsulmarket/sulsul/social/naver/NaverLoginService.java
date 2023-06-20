package com.sulsulmarket.sulsul.social.naver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
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

    /**
     * naver login 인증 요청하는 메서드 return by client_id, redirect-url
     */
    public String getAuthorizationUrl() {
        return "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&response_type=code";
    }

    /**
     * 위에서 인증 후 받아온 code + 필수 파라미터를 넘기고 Create Token 가지고 userInfo Get
     */
    public NaverUserDTO getUserInfoByAccessToken(String code) {
        String tokenUrl = "https://nid.naver.com/oauth2.0/token";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUrl);
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        log.info("[INFO] Create Token Param -> client_id {}, client_secret {}, redirect_uri {}, code", clientId, clientSecret, redirectUrl, code);

        HttpHeaders tokenHeaders = new HttpHeaders();
        // Header 세팅 "application/x-www-form-urlencoded" type
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HttpEntity 생성 위에 params, header 담음
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, tokenHeaders);
        RestTemplate restTemplate = new RestTemplate();

        try {

            // restTemplate -> post(request url, entity(body, header), return class)
            ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, requestEntity, String.class);
            // response.getBody 메서드를 통해 token 정보를 가져옴
            String accessToken = response.getBody();
            log.info("[INFO] accessToken -> {}", accessToken);

            // Json parsing 을 위한 ObjetMapper
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // Json 객체의 특정 필드 값을 가져옴
                JsonNode jsonNode = objectMapper.readTree(accessToken);
                // JsonNode access_token By String type return
                String getToken = jsonNode.get("access_token").asText();

                // 토큰을 사용하여 회원 정보를 가져오는 API Call
                String getUserInfoUrl = "https://openapi.naver.com/v1/nid/me";
                HttpHeaders userInfoHeader = new HttpHeaders();
                userInfoHeader.set("Authorization", "Bearer " + getToken);

                HttpEntity<String> userInfoEntity = new HttpEntity<>(userInfoHeader);
                ResponseEntity<String> responseEntity = restTemplate.exchange(getUserInfoUrl, HttpMethod.GET, userInfoEntity, String.class);

                String userInfo = responseEntity.getBody();
                log.info("[INFO] userInfo -> {}", userInfo);

                NaverUserDTO userDTO = objectMapper.readValue(userInfo, NaverUserDTO.class);
                return userDTO;

            } catch (IOException e) {
                log.error("[ERROR] Failed to parse token response or user info", e);
                throw new IllegalStateException("Failed to parse token response or user info");
            }
        } catch (HttpClientErrorException e) {
            log.error("[ERROR] Failed to get user info from Naver API", e);
            throw new IllegalStateException("Failed to get user info from Naver API", e);
        }
    }
}