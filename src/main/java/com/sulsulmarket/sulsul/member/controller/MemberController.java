package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<Object> getMemberList() {

        try {
            List<Member> memberList = memberService.getMemberList();
            return new ResponseEntity<>(memberList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Member Controller Exception : {}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 아이디로 회원정보 조회 컨트롤러
     */
    @PostMapping("/info")
    public ResponseEntity<Object> getMemberById(@RequestBody Map<String, String> param) {

        try {
            Member member = memberService.getMemberById(param.get("memberId"));
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원 로그인 컨틀롤러
     */
//    @PostMapping("/api/member/login")
//    public ResponseEntity<Object> loginMember(@RequestBody Map<String, String> parameter) {
//
//        try {
//            LoginMemberDTO loginMember = memberService.memberLogin(parameter.get("memberId"), parameter.get("password"));
//            return ResponseEntity.status(HttpStatus.OK).body(loginMember);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    /**
     * 회원가입 컨트롤러
     */
    @PostMapping("/sign")
    public ResponseEntity<Object> memberSign(@RequestBody Member member) {

        try {
            memberService.memberSign(member);
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
    public ResponseEntity<Object> duplicateEmail(@RequestBody Member member) {

        try {
            memberService.duplicateCheckEmail(member.getMEMBER_EMAIL());
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 이메일입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 패스워드 변경 컨트롤러
     */
    @PostMapping("/api/member/new/password")
    public ResponseEntity<Object> updateNewPassword(@RequestBody Member member) {

        try {
            memberService.memberPasswordUpdate(member.getMEMBER_PW(), member.getMEMBER_ID());
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
