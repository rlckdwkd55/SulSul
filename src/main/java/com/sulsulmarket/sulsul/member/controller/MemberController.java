package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.member.dto.LoginMemberDTO;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/api/member/list")
    public ResponseEntity<Object> getMemberList() {

        try {
            List<MemberDTO> memberDTO = memberService.getMemberList();
            log.info("[INFO] Member List Size : -> {}", memberDTO.size());
            return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
        } catch (Exception e) {
            log.info("[ERROR] : -> Not Found Member List ! ! !");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    /**
     * 아이디로 회원 조회 컨트롤러
     */
    @PostMapping("/api/member/id")
    public ResponseEntity<Object> getMemberById(@RequestBody MemberDTO memberDTO) {

        try {
            MemberDTO member = memberService.getMemberById(memberDTO);
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 아이디 중복 체크 컨트롤러
     */
    @PostMapping("/api/duplicate/check/id")
    public ResponseEntity<Object> duplicateCheckId(@RequestBody MemberDTO memberDTO) {

        try {
            memberService.duplicateCheckId(memberDTO.getMEMBER_ID());
            return ResponseEntity.status(HttpStatus.OK).body("해당 아이디는 사용 가능한 아이디입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 회원 로그인 컨틀롤러
     */
    @PostMapping("/api/member/login")
    public ResponseEntity<Object> loginMember(@RequestBody Map<String, String> parameter) {

        try {
            LoginMemberDTO loginMember = memberService.memberLogin(parameter.get("memberId"), parameter.get("password"));
            return ResponseEntity.status(HttpStatus.OK).body(loginMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 회원가입 컨트롤러
     */
    @PostMapping("/api/member/sign")
    public ResponseEntity<Object> memberSign(@Validated @RequestBody MemberDTO memberDTO) {

        try {
            memberService.memberSign(memberDTO);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공하였습니다.");
        } catch (Exception e) {
            log.error("MEMBER SIGN FAIL ! ! ! -> [{}]", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 이메일 중복 체크 컨트롤러
     */
    @PostMapping("/api/duplicate/check/email")
    public ResponseEntity<Object> duplicateEmail(@RequestBody MemberDTO memberDTO) {

        try {
            memberService.duplicateCheckEmail(memberDTO.getMEMBER_EMAIL());
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 이메일입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 패스워드 변경 컨트롤러
     */
    @PostMapping("/api/member/new/password")
    public ResponseEntity<Object> updateNewPassword(@RequestBody MemberDTO memberDTO) {

        try {
            memberService.memberPasswordUpdate(memberDTO.getMEMBER_PW(), memberDTO.getMEMBER_ID());
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
