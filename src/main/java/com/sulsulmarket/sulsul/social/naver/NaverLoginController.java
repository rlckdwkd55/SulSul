package com.sulsulmarket.sulsul.social.naver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/api/login/naver")
    public String naverLogin() {
        String authorizationUrl = naverLoginService.getAuthorizationUrl();
        log.info("[INFO] authorizationUrl -> {}", authorizationUrl);
        return "redirect:" + authorizationUrl;
    }

    @PostMapping("/api/login/naver/callback")
    public ResponseEntity<String> naverLoginCallback(@RequestParam String code) {
        String tokenResponse = naverLoginService.getTokenResponse(code);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/api/userInfo")
    public ResponseEntity<NaverUserDTO> getUserInfo(@RequestParam String code) {
        NaverUserDTO userInfo = naverLoginService.getUserInfo(code);
        return ResponseEntity.ok(userInfo);
    }
}
