package com.sulsulmarket.sulsul.member.controller;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/api/memeber/list")
    public ResponseEntity<Object> getMemberList() {

        List<MemberDTO> memberDTO = memberService.getMemberList();

        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @PostMapping("/api/member/id")
    public ResponseEntity<Object> getMemberById(@RequestBody String memberId) {

        MemberDTO member = memberService.getMemberById(memberId);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    //response http status code, user id, user name, redirect url
//    @PostMapping("/login")
//    public String

}
