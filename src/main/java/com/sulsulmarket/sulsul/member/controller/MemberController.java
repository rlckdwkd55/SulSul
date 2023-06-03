package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    @PostMapping("/api/member/id")
    public ResponseEntity<Object> getMemberById(@RequestBody MemberDTO memberDTO) {

        try {
            MemberDTO member = memberService.getMemberById(memberDTO.getMEMBER_ID());
            return ResponseEntity.status(HttpStatus.OK).body(member);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/duplicate/check")
    public ResponseEntity<Object> duplicateCheckId(@RequestBody MemberDTO memberDTO) {

        try {
            memberService.duplicateCheckId(memberDTO.getMEMBER_ID());
            return ResponseEntity.status(HttpStatus.OK).body("해당 아이디는 사용 가능한 아이디입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/member/login")
    public ResponseEntity<Object> loginMember(@RequestBody MemberDTO memberDTO) {

        try {
            MemberDTO loginMember = memberService.memberLogin(memberDTO.getMEMBER_ID(), memberDTO.getMEMBER_PW());
            return ResponseEntity.status(HttpStatus.OK).body(loginMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/member/sign")
    //TODO DTO Field Validation Check
    public ResponseEntity<Object> memberSign(@RequestBody MemberDTO memberDTO) {

        try {
            MemberDTO signMember = memberService.memberSign(memberDTO);
            return ResponseEntity.status(HttpStatus.OK).body(signMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/member/email")
    public ResponseEntity<Object> duplicateEmail(@RequestBody MemberDTO memberDTO) {

        try {
            memberService.duplicateCheckEmail(memberDTO.getMEMBER_EMAIL());
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 이메일입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/member/new/password")
    public ResponseEntity<Object> updateNewPassword(@RequestBody MemberDTO memberDTO) {

        try {
            String newPassword = memberService.memberPasswordUpdate(memberDTO.getMEMBER_PW(), memberDTO.getMEMBER_ID());
            return ResponseEntity.status(HttpStatus.OK).body(newPassword);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
