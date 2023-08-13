package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
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
            if(e instanceof BadSqlGrammarException){
                log.error("Exception : ", e);
                return new ResponseEntity<>("DB Insert 실패.", HttpStatus.INTERNAL_SERVER_ERROR);
                //TODO DB 에러에 대한 재시도, 뭐 여러가지 분기처리를 할 수 있겠지
            } else if (e instanceof IllegalArgumentException) {
                log.error("Exception : ", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.error("Exception : ", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * 회원 로그인 컨틀롤러
     */
    @PostMapping("/login")
    public ResponseEntity<Object> loginMember(@RequestBody Map<String, String> parameter) {

        try {
            memberService.memberLogin(parameter.get("memberId"), parameter.get("password"));
            return new ResponseEntity<>("로그인 성공" + parameter.get("memberId").toString(), HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                log.error("member login IllegalArgumentException : ", e);
                return new ResponseEntity<>("비밀번호가 일치 하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
            log.error("member login Exception : ", e);
            return new ResponseEntity<>("로그인 실패", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원가입 컨트롤러
     */
    @PostMapping("/sign")
    public ResponseEntity<Object> memberSign(@RequestBody Member member) {

        try {
            memberService.memberSign(member);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공하였습니다.");
        } catch (Exception e) {
            log.error("Member Sign Exception : ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 아이디 중복 체크 컨트롤러
     */
    @PostMapping("/duplicate/id")
    public ResponseEntity<String> duplicateIdCheck(@RequestBody Map<String, String> param) {
        try {
            memberService.duplicateCheckId(param.get("memberId"));
            return new ResponseEntity<>("사용 가능한 아이디입니다.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Member Duplicate Id Check Exception :", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * 이메일 중복 체크 컨트롤러
     */
    @PostMapping("/duplicate/email")
    public ResponseEntity<Object> duplicateEmailCheck(@RequestBody Map<String, String> param) {

        try {
            memberService.duplicateCheckEmail(param.get("memberEmail"));
            return new ResponseEntity<>("사용 가능한 이메일입니다.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Member Duplicate Email Check Exception :", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
