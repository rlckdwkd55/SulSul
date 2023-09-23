package com.sulsulmarket.sulsul.social.kakao;

import com.sulsulmarket.sulsul.dto.social.kakao.KakaoUserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/kakao/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KakaoLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    @GetMapping("/url")
    public ResponseEntity<Object> redirectUrlRequest() {

        try {
            String response = kakaoLoginService.getRedirectUrl();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("kakao Login Exception : ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/info")
    public ResponseEntity<Object> getUserInfo(@RequestParam String code) {
        try {
            KakaoUserInfoResponse response = kakaoLoginService.getUserInfo(code);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}