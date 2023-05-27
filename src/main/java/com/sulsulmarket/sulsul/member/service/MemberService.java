package com.sulsulmarket.sulsul.member.service;

import com.sulsulmarket.sulsul.Util.SulSulUil;
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

    public List<MemberDTO> getMemberList() {

        List<MemberDTO> memberList = memberDao.getMemberList();
        if (memberList == null || memberList.isEmpty()) {
            log.error("[ERROR] -> Not Found Member List ! ! !");
            throw new NullPointerException("회원 리스트 조회 결과 없습니다.");
        }
        log.info("[INFO] -> Member List Select ALL SUCCESS ! ! !");
        return memberList;
    }

    public MemberDTO getMemberById(String id) {

        MemberDTO memberDTO = memberDao.getMemberById(id);

        if(memberDTO == null) {
            log.error("[ERROR] -> Not Found Member By Id");
            throw new NullPointerException("아이디 조회 결과 해당 회원을 찾을 수가 없습니다");
        }
        log.info("[INFO] -> Member Select By Id SUCCESS ! ! !");
        return memberDTO;
    }

    public void duplicateCheckId(String id) {

        MemberDTO duplicateMember = memberDao.getMemberById(id);

        // 있으면 중복
        if(duplicateMember != null) {
            log.info("[ERROR] Is Exist Duplicate Id -> {}", duplicateMember.getMEMBER_ID());
            throw new DuplicateKeyException("이미 존재하는 아이디 입니다.");
        }
        log.info("[INFO] -> Use Ok Id -> {}", id);
    }

    public MemberDTO memberLogin(String id, String password) {

        // 해당 멤버 아이디로 회원이 있는지 먼저 조회
        MemberDTO loginMember = memberDao.getMemberById(id);

        if(loginMember == null) {
            log.error("[ERROR] -> Not Found Member By Id");
            throw new NullPointerException("아이디 조회 결과 회원이 없습니다.");
        }

        // 위에서 아이디로 조회한 회원 정보의 비밀번호를 검사
        if (!passwordEncoder.matches(password, loginMember.getMEMBER_PW())) {
            log.error("[ERROR] -> Invalid Password ! ! ! ");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        log.info("[INFO] -> Member Login SUCCESS -> {}", loginMember.getMEMBER_NAME());
        return loginMember;
    }

    public MemberDTO memberSign(MemberDTO memberDTO) {

        // 아이디 중복 체크
        duplicateCheckId(memberDTO.getMEMBER_ID());

        // 중복되지 않은 아이디인 경우
        log.info("MEMBER_PWD -> {}", memberDTO.getMEMBER_PW());
        // 암호화
        String password = passwordEncoder.encode(memberDTO.getMEMBER_PW());
        log.info("PASSWORD ENCODER -> {}", password);
        // 암호화 한 값을 대입
        memberDTO.setMEMBER_PW(password);
        // 위에서 암호화 한 memberDTO 넘겨주면서 회원가입
        Integer insertMember = memberDao.memberSign(memberDTO);

        if (insertMember == null || insertMember <= 0) {
            log.error("[ERROR] -> Member Sign Fail ! ! ! Insert Count -> {}", insertMember);
            throw new NullPointerException("회원 가입 실패");
        }

        log.info("[INFO] -> Member Sign SUCCESS ! ! ! -> {}", memberDTO.getMEMBER_ID());
        return memberDTO;
    }

    public void duplicateCheckEmail(String email) {

        MemberDTO duplicateMember = memberDao.getMemberEmail(email);

        if(duplicateMember != null) {
            log.info("[ERROR] Is Exist Duplicate Email -> {}", duplicateMember.getMEMBER_EMAIL());
            throw new DuplicateKeyException("이미 존재하는 아이디 입니다.");
        }
        log.info("[INFO] -> Use Ok Email -> {}", email);
    }

    public int memberPasswordUpdate(String newPassword, String id) {

        Integer updateCount = memberDao.memberPasswordUpdate(newPassword, id);

        if(updateCount == null || updateCount <= 0) {
            log.info("[ERROR] Update Fail Member Password");
        }
        return updateCount;
    }
}