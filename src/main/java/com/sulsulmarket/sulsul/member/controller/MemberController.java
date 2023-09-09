package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.member.MemberOne;
import com.sulsulmarket.sulsul.dto.member.SignRequestMember;
import com.sulsulmarket.sulsul.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 이메일로 로그인 하기. 회원 정보 조회도 가능.
     */
    @PostMapping("/info")
    public ResponseEntity<Object> getMemberById(@RequestBody Map<String, String> param) {

        try {
            MemberOne member = memberService.getMemberById(param.get("email"));
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원가입 컨트롤러
     */
    @PostMapping("/sign")
    public ResponseEntity<Object> memberSign(@RequestBody SignRequestMember member) {

        try {
            memberService.memberSign(member);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공하였습니다.");
        } catch (Exception e) {
            log.error("MEMBER SIGN FAIL ! ! ! -> [{}]", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
