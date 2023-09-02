package com.sulsulmarket.sulsul.social.naver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulsulmarket.sulsul.dto.naver.NaverUser;
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

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String tokenUrl;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String userInfoUrl;

    /**
     * naver login 인증 요청하는 메서드 return by client_id, redirect-url
     */
    public String getAuthorizationUrl() {
        return "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&response_type=code";
    }

    /**
     * 위에서 인증 후 받아온 code + 필수 파라미터를 넘기고 Create Token 가지고 회원 정보를 가져온다.
     */
    public NaverUser getUserInfoByAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUrl);
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        log.debug("Create Token Param -> client_id {}, client_secret {}, redirect_uri {}, code", clientId, clientSecret, redirectUrl, code);

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
            log.info("naver login success response : {}", response);
            log.info("accessToken -> {}", accessToken);

            // Json parsing 을 위한 ObjetMapper
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // Json 객체의 특정 필드 값을 가져옴
                JsonNode jsonNode = objectMapper.readTree(accessToken);
                // JsonNode access_token By String type return
                String getToken = jsonNode.get("access_token").asText();

                // 토큰을 사용하여 회원 정보를 가져오는 API Call
                HttpHeaders userInfoHeader = new HttpHeaders();
                userInfoHeader.set("Authorization", "Bearer " + getToken);

                HttpEntity<String> userInfoEntity = new HttpEntity<>(userInfoHeader);
                ResponseEntity<String> responseEntity = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoEntity, String.class);

                String userInfo = responseEntity.getBody();
                log.info("user info get success response : {}", responseEntity);
                log.info("userInfo -> {}", userInfo);

                NaverUser userDTO = objectMapper.readValue(userInfo, NaverUser.class);
                return userDTO;

            } catch (Exception e) {
                log.error("Failed to parse token", e);
                throw new IllegalStateException("토큰으로 회원 정보 가져오기 실패.");
            }
        } catch (HttpClientErrorException e) {
            log.error("Failed to get token from  client", e);
            throw new IllegalStateException("토큰 조회 실패");
        }
    }
}