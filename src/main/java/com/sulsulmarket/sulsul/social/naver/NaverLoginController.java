package com.sulsulmarket.sulsul.social.naver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class NaverLoginController {

    private final NaverLoginService naverLoginService;

    @Autowired
    public NaverLoginController(NaverLoginService naverLoginService) {
        this.naverLoginService = naverLoginService;
    }

    /**
     * Naver Login redirect url
     * 로그인 페이지 이동 후 인증 요청 Controller
     */
    @GetMapping("/api/login/naver")
    public String naverLogin() {
        String authorizationUrl = naverLoginService.getAuthorizationUrl();
        log.info("[INFO] authorizationUrl -> {}", authorizationUrl);

        return "redirect:" + authorizationUrl;
    }

    /**
     * 위에서 받아온 authorization_code parameter 받아 createToken 발급 바독 해당 Token 가지고 User Info Get
     */
    @PostMapping("/api/oauth2/naver/callback")
    public ResponseEntity<Object> naverLoginCallback(@RequestParam String code) {

        try {
            NaverUserDTO userDTO = naverLoginService.getUserInfoByAccessToken(code);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
