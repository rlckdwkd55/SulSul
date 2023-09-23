






package com.sulsulmarket.sulsul.social.kakao;

import com.google.gson.Gson;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoAuthDTO;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoCustomResponse;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoTokenResponse;
import com.sulsulmarket.sulsul.dto.social.kakao.KakaoUserInfoResponse;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
public class KakaoLoginService {

    private String url = "https://kauth.kakao.com/oauth/authorize";
    @Autowired
    private KakaoAuthDTO kakaoAuth;
    @Autowired
    private MemberDao memberDao;

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

    public KakaoCustomResponse getUserInfo(String code) {

        String token = tokenRequest(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(kakaoAuth.getUserInfoUri(), HttpMethod.GET, entity, String.class);
            log.info("Response Check : {}", response.getBody());
            KakaoUserInfoResponse userInfo = gson.fromJson(response.getBody(), KakaoUserInfoResponse.class);

            if (userInfo == null) {
                throw new NullPointerException("카카오 유저 정보가 없습니다.");
            }

            KakaoCustomResponse customResponse = KakaoCustomResponse
                    .builder()
                    .kakaoUserInfoResponse(userInfo)
                    .resultcode("00").build();

            // 이미 가입된 회원인 경우에
            if (memberDao.getMemberByEmail(userInfo.getKakao_account().getEmail()) != null) {
                log.warn("Is Exist Naver Member.");
                customResponse.setResultcode("01");
            }

            // 미성년자인 경우 체크할 수가 없음 생년월일 제공을 안해줌
//            if (LocalDateTime.now().getYear() <  Integer.parseInt(userInfo.get().getBirthyear()) + 19) {
//                log.warn("is no adult member sign fail.");
//                customResponse.setResultcode("02");
//            }

            log.info("kakao get userInfo Success : [{}]", userInfo);

            return customResponse;
        } catch (Exception e) {
            log.error("Kakao User Info Response Exception.", e);
            return null;
        }
    }
}