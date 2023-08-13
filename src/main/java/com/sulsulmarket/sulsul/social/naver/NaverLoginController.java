package com.sulsulmarket.sulsul.social.naver;

import com.sulsulmarket.sulsul.dto.naver.NaverUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/naver")
@Slf4j
public class NaverLoginController {

    @Autowired
    private NaverLoginService naverLoginService;

    /**
     *
     * @return redirect url
     */
    @GetMapping("/naver/login")
    public ResponseEntity<String> naverLogin() {
        try {
            String authorizationUrl = naverLoginService.getAuthorizationUrl();
            log.info("authorizationUrl -> {}", authorizationUrl);
            return new ResponseEntity<>(authorizationUrl, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception : {}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 위에서 받아온 authorization_code parameter 받아 createToken 발급 바독 해당 Token 가지고 User Info Get
     */
    @PostMapping("/naver/callback")
    public ResponseEntity<Object> naverLoginCallback(@RequestParam String code) {

        try {
            NaverUser userInfo = naverLoginService.getUserInfoByAccessToken(code);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception : {}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
