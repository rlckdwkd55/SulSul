package com.sulsulmarket.sulsul.social.naver;

import com.sulsulmarket.sulsul.dto.social.naver.NaverUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


@RestController
@RequestMapping("/naver/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class NaverLoginController {

    @Autowired
    private NaverLoginService naverLoginService;

    /**
     *
     * @return redirect url
     */
    @GetMapping("/url")
    public ResponseEntity<String> naverLogin() {

        try {
            String authorizationUrl = naverLoginService.getAuthorizationUri();
            return new ResponseEntity<>(authorizationUrl, HttpStatus.OK);
        } catch (Exception e) {
            log.error("naver login url request controller Exception.", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 위에서 받아온 authorization_code parameter 받아 createToken 발급 바독 해당 Token 가지고 User Info Get
     * @return userInfo from Naver.
     */
    @GetMapping("/user/info")
    public ResponseEntity<Object> naverUserGetInfo(@RequestParam String code) {

        try {
            NaverUser naverUser = naverLoginService.naverUserInfoGet(code);
            return new ResponseEntity<>(naverUser, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                log.error("naver get user info is NullPointerException.", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            } else if (e instanceof HttpClientErrorException) {
                log .error("naver user info HttpClientErrorException.", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.error("naver user info Exception.", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
