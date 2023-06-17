package com.sulsulmarket.sulsul.member.service;

import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 회원 전체 리스트 가져오는 메서드
     */
    public List<MemberDTO> getMemberList() {

        List<MemberDTO> memberList = memberDao.getMemberList();
        if (memberList == null || memberList.isEmpty()) {
            log.error("[ERROR] -> Not Found Member List ! ! !");
            throw new NullPointerException("회원 리스트 조회 결과 없습니다.");
        }
        log.info("[INFO] -> Member List Select ALL SUCCESS ! ! !");
        return memberList;
    }

    /**
     * 회원 아이디로 개인 정보 조회 메서드
     */
    public MemberDTO getMemberById(MemberDTO memberDTO) {

        if (memberDTO.getMEMBER_ID() == null) {
            log.error("MEMBER ID IS NULL ! ! !");
            throw new NullPointerException("아이디 값이 없습니다.");
        }

        MemberDTO memberExist = memberDao.getMemberById(memberDTO.getMEMBER_ID());

        if(memberExist == null) {
            log.error("NOT FOUND MEMBER BY ID ! ! !");
            throw new NullPointerException("아이디 조회 결과 해당 회원을 찾을 수가 없습니다");
        }
        log.info("MEMBER SELECT BY ID SUCCESS ! ! ! -> {}", memberDTO.getMEMBER_ID());
        return memberDTO;
    }

    /**
     * 아이디 중복 체크 메서드
     */
    public void duplicateCheckId(String id) {

        if(id == null) {
            log.error("MEMBER ID IS NULL ! ! !");
            throw new NullPointerException("아이디 값이 없습니다.");
        } else {
            MemberDTO duplicateMember = memberDao.getMemberById(id);
            // 있으면 중복
            if(duplicateMember != null) {
                log.info("IS EXIST DUPLICATE ID ! ! ! -> {}", id);
                throw new DuplicateKeyException("이미 존재하는 아이디 입니다.");
            }
            log.info("USED ID OK ! ! ! -> {}", id);
        }
    }

    /**
     * 이메일 중복 체크 메서드
     */
    public void duplicateCheckEmail(String email) {

        if(email == null) {
            log.error("MEMBER EMAIL IS NULL ! ! !");
            throw new NullPointerException("이메일 값이 없습니다.");
        } else {
            boolean duplicateEmail = memberDao.getMemberByEmail(email);
            if(duplicateEmail) {
                log.info("IS EXIST DUPLICATE EMAIL -> {}", email);
                throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            }
            log.info("USED EMAIL OK ! ! ! -> {}", email);
        }
    }

    public MemberDTO memberLogin(MemberDTO memberDTO) {

        MemberDTO loginMember = null;
        // 해당 멤버 아이디로 회원이 있는지 먼저 조회
        loginMember = memberDao.getMemberById(memberDTO.getMEMBER_ID());

        // FALSE 경우 -> ERROR
        if(loginMember == null) {
            log.error("NOT FOUND MEMBER BY ID");
            throw new NullPointerException("아이디 조회 결과 회원이 없습니다.");
        }

        // 위에서 받은 패스워드랑 아이디로 조회한 회원 정보의 비밀번호를 검사 ! ! !
        if (!passwordEncoder.matches(memberDTO.getMEMBER_PW(), loginMember.getMEMBER_PW())) {
            log.error("INVALID PASSWORD ! ! ! ");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        log.info("MEMBER LOGIN SUCCESS -> {}", memberDTO.getMEMBER_ID());
        return loginMember;
    }

    public MemberDTO memberSign(MemberDTO memberDTO) {

        // 아이디 중복 체크
        duplicateCheckId(memberDTO.getMEMBER_ID());

        // 이메일 중복 체크
        duplicateCheckEmail(memberDTO.getMEMBER_EMAIL());

        // 중복 검증 끝나고 패스워드 암호화
        String password = passwordEncoder.encode(memberDTO.getMEMBER_PW());
        log.info("PASSWORD ENCODER -> {}", password);
        // 암호화 한 값을 대입
        memberDTO.setMEMBER_PW(password);
        // 위에서 암호화 한 memberDTO 넘겨주면서 회원가입
        Integer insertMember = memberDao.memberSign(memberDTO);

        if (insertMember == null || insertMember <= 0) {
            log.error("MEMBER SIGN FAIL ! ! !");
            throw new NullPointerException("회원 가입 실패");
        }

        log.info("MEMBER SIGN SUCCESS ! ! ! -> {}", memberDTO.getMEMBER_ID());
        return memberDTO;
    }

    public String memberPasswordUpdate(String newPassword, String id) {

        String encodePassword = passwordEncoder.encode(newPassword);
        Integer updateCount = memberDao.memberPasswordUpdate(encodePassword, id);

        System.out.println(updateCount);
        if(updateCount == null || updateCount <= 0) {
            log.info("[ERROR] -> Update Fail Member Password");
            throw new NullPointerException("패스워드 변경에 실패했습니다");
        }
        log.info("[INFO] -> update new password Success {}", newPassword);
        return newPassword;
    }
}