package com.sulsulmarket.sulsul.member.service;

import com.sulsulmarket.sulsul.member.dao.MemberDao;
import com.sulsulmarket.sulsul.member.mapper.MemberMapper;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberDao memberDao;
    private PasswordEncoder passwordEncoder;

    public List<MemberDTO> getMemberList() {

        List<MemberDTO> memberList = memberDao.getMemberList();

        if(Objects.isNull(memberList)) {
            log.error("ERROR -> Not Found Member List ! ! !");
            throw new NullPointerException("회원 리스트 조회 결과 없습니다.");
        }

        return memberList;
    }

    public MemberDTO getMemberById(String id) {

        MemberDTO memberDTO = memberDao.getMemberById(id);

        if(Objects.isNull(memberDTO)) {
            log.error("ERROR -> Not Found Member By Id");
            throw new NullPointerException("아이디로 조회 결과 해당 회원이 없습니다");
        }

        return memberDTO;
    }

    public boolean duplicateCheckId(String id) {

        MemberDTO duplicateId = memberDao.getMemberById(id);

        if(duplicateId != null) {
            log.info("[INFO] Is Exist Duplicate Id ! ! !");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "중복된 아이디입니다.");
        }
        log.info("[INFO] -> {} 사용 가능한 아이디입니다.", id);
        return true;
    }

    public void memberLogin(String id, String password) {

        MemberDTO loginMember = memberDao.memberLogin(id, password);

        if(Objects.isNull(loginMember)) {
            log.error("[ERROR] -> Not Found Member By Id, Password");
            throw new NullPointerException("아이디, 패스워드 조회 시 해당 회원이 없습니다.");
        }
    }

    public MemberDTO memberSign(MemberDTO memberDTO) {

        boolean validateId = duplicateCheckId(memberDTO.getMEMBER_ID());

        // 중복된 아이디인 경우
        if (!validateId) {
            log.info("[INFO] 중복된 아이디입니다.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "중복된 아이디입니다.");
        }

        // 중복되지 않은 아이디인 경우
        log.info("MEMBER_PWD -> {}", memberDTO.getMEMBER_PW());
        String password = passwordEncoder.encode(memberDTO.getMEMBER_PW());
        log.info("PASSWORD ENCODER -> {}", password);
        memberDTO.setMEMBER_PW(password);
        log.info("MEMBER_PASSWORD ENCODE -> {}", memberDTO.getMEMBER_PW());
        Integer insertMember = memberDao.memberSign(memberDTO);

        if (insertMember == null || insertMember == 0) {
            log.error("[ERROR] 회원 가입 실패");
            throw new RuntimeException("회원 가입 실패");
        } else {
            return memberDTO;
        }
    }
}
