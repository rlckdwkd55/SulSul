package com.sulsulmarket.sulsul.social.naver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.dto.social.naver.NaverTokenResponse;
import com.sulsulmarket.sulsul.dto.social.naver.NaverUser;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class NaverLoginService {

    @Autowired
    private MemberDao memberDao;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String authorizationUri;
    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String userInfoUri;
    private MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    private HttpHeaders headers = new HttpHeaders();
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    /**
     * naver login URL 인증 요청하는 메서드 return by client_id, redirect-url
     * Naver Login을 할 수 있는 URL 을 만들어서 프론트에게 넘겨준다.
     */
    public String getAuthorizationUri() {
        log.debug("naver AuthorizationUrl : [{}]", authorizationUri + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&response_type=code");
        return authorizationUri + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&response_type=code" + "&state=";
    }

    /**
     * 위에서 인증 후 받아온 code + 필수 파라미터를 넘기고 Create Token.
     */
    private String naverTokenRequest(String code) {

        // naver token 요청에 필요한 정보 기입. 데이터가 1억개일 경우.
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        log.debug("Create Token Request Parameter -> client_id : [{}], client_secret : [{}], redirect_uri : [{}], code : [{}]", clientId, clientSecret, redirectUri, code);

        // Token Request Header 세팅 "application/x-www-form-urlencoded" type
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HttpEntity 생성 위에 params, header 담음
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        try {
            // restTemplate -> post(request url, entity(body, header), return class)
            ResponseEntity<String> response = restTemplate.postForEntity(tokenUri, requestEntity, String.class);

            NaverTokenResponse tokenResponse = null;
            if (response.getStatusCode() == HttpStatus.OK) {
                tokenResponse = gson.fromJson(response.getBody(), NaverTokenResponse.class);
            } else {
                // 만약 에러 코드랑 에러 메시지가 있을 떄 네이버 토큰 요청 시에 에러가 날 경우에만 존재
                if (!SulSulUtil.strNullCheck(tokenResponse.getError()) && !SulSulUtil.strNullCheck(tokenResponse.getError_description())) {
                    naverErrorHandler(tokenResponse.getError(), tokenResponse.getError_description());
                }
            }

            log.info("naver token response : [{}]", tokenResponse);
            return tokenResponse.getAccess_token();

        } catch (HttpClientErrorException e) {
            log.error("naver token request Exception.", e);
            return null;
        } finally {
            params.clear();
            headers.clear();
        }
    }

    /**
     * 위에서 토큰 요청한 값을 가지고 유저 정보 가져오는 메서드.
     * @return NaverUser
     */
    public NaverUser naverUserInfoGet(String code) {

        if (!SulSulUtil.strNullCheck(code)) {
            log.error("naver user info code is null.");
            throw new NullPointerException("파라미터 코드 값이 없습니다.");
        }

        // 토큰 반환 값.
        String accessToken = naverTokenRequest(code);
        headers.setBearerAuth(accessToken);
        HttpEntity<String> userInfoEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, userInfoEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                NaverUser userInfo = gson.fromJson(response.getBody(), NaverUser.class);
                if (userInfo == null) {
                    throw new NullPointerException("네이버 유저 정보가 없습니다.");
                }

                // 이미 가입된 회원인 경우에
                if (memberDao.getMemberByEmail(userInfo.getResponse().getEmail()) != null) {
                    log.warn("Is Exist Naver Member.");
                    userInfo.setResultcode("01");
                }

                // 미성년자인 경우
                if (LocalDateTime.now().getYear() >= Integer.parseInt(userInfo.getResponse().getBirthyear()) + 19) {
                    log.warn("is no adult member sign fail.");
                    userInfo.setResultcode("02");
                }

                log.info("naver get userInfo Success : [{}]", userInfo);
                return userInfo;

            } else {
                log.error("Naver User Info Request HttpStatus Code : [{}]", response.getStatusCode());
                throw new HttpClientErrorException(response.getStatusCode(), "네이버 유저 정보 요청 에러.");
            }

        } catch (Exception e) {
            log.error("naver login user info get Exception.", e);
            return null;
        } finally {
            params.clear();
            headers.clear();
        }
    }

    /**
     * Naver API 요청 시 나는 Error Code Handler
     */
    private void naverErrorHandler(String errorCode, String errorDescription) throws HttpClientErrorException {

        switch (errorCode) {
            case "024":
                log.error("naver Authentication failed errorCode : [{}], description : [{}]", errorCode, errorDescription);
                break;
            case "028":
                log.error("naver Authentication header not exists errorCode : [{}], description : [{}]", errorCode, errorDescription);
                break;
            case "403":
                log.error("naver Forbidden errorCode : [{}], description : [{}]", errorCode, errorDescription);
                break;
            case "500":
                log.error("naver System Error errorCode : [{}], description : [{}]", errorCode, errorDescription);
                break;
            default:
                log.error("naver Unknown failed errorCode : [{}], description : [{}]", errorCode, errorDescription);
                break;
        }
    }
}