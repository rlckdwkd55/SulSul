package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.dto.member.MemberOne;
import com.sulsulmarket.sulsul.dto.member.SignRequestMember;
import com.sulsulmarket.sulsul.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 회원 정보 조회.
     */
    @PostMapping("/info")
    public ResponseEntity<Object> getMemberByEmail(@RequestBody Map<String, String> param) {

        try {
            MemberOne member = memberService.getMemberById(param.get("email"));
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 이메일로 로그인 하기
     */
    @PostMapping("/login")
    public ResponseEntity<Object> memberLogin(@RequestBody Map<String, String> param) {
        try {
            if (memberService.memberLogin(param.get("email"))) {
                log.info("member login is success.");
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                log.info("member login is fail..");
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("member login is exception.", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 회원가입 컨트롤러
     */
    @PostMapping("/sign")
    public ResponseEntity<Object> memberSign(@RequestBody SignRequestMember member) {

        try {
            if (memberService.memberSign(member)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("member sign is exception", e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
