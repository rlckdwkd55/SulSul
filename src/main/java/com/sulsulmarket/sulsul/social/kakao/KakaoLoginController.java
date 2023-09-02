package com.sulsulmarket.sulsul.social.kakao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/kakao/login")
public class KakaoLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    @GetMapping("/url")
    public ResponseEntity<Object> redirectUrlRequest() {

        try {
            String response = kakaoLoginService.getRedirectUrl();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Kakao Login Exception : ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/toekn/request")
    public ResponseEntity<Object> kakaoToeknRequest(@RequestParam String code) {
        try {
            kakaoLoginService.tokenRequest(code);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/info")
    public ResponseEntity<Object> getUserInfo(@RequestParam String token) {
        try {
            kakaoLoginService.getUserInfo(token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}