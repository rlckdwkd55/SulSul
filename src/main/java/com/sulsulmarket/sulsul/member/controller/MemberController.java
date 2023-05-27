package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.member.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/api/member/list")
    public ResponseEntity<Object> getMemberList() {

        try {
            List<MemberDTO> memberDTO = memberService.getMemberList();
            return new ResponseEntity<>(memberDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/member/id")
    public ResponseEntity<Object> getMemberById(@RequestBody MemberDTO memberDTO) {
        //TODO memberDTO를 프론트에서 당연히 null check 하겠지만, 검증 하는 메소드를 만들거나, 하셔서 핸들링 해주세요
        try {
            MemberDTO member = memberService.getMemberById(memberDTO.getMEMBER_ID());
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/duplicate/check")
    public ResponseEntity<Object> duplicateCheckId(@RequestBody MemberDTO memberDTO) {
        //TODO memberDTO를 프론트에서 당연히 null check 하겠지만, 검증 하는 메소드를 만들거나, 하셔서 핸들링 해주세요

        try {
            memberService.duplicateCheckId(memberDTO.getMEMBER_ID());
            return new ResponseEntity<>("해당 아이디는 사용 가능한 아이디입니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/api/member/login")
    public ResponseEntity<Object> loginMember(@RequestBody MemberDTO memberDTO) {
        //TODO memberDTO를 프론트에서 당연히 null check 하겠지만, 검증 하는 메소드를 만들거나, 하셔서 핸들링 해주세요

        try {
            memberService.memberLogin(memberDTO.getMEMBER_ID(), memberDTO.getMEMBER_PW());
            return new ResponseEntity<>("로그인 성공 ! ! !", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/member/sign")
    public ResponseEntity<Object> memberSign(@Validated @RequestBody MemberDTO memberDTO) {

        try {
            MemberDTO signMember = memberService.memberSign(memberDTO);
            return new ResponseEntity<>(signMember, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //response http status code, user id, user name, redirect url
//    @PostMapping("/login")
//    public String

}
