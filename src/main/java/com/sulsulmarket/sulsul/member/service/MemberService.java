package com.sulsulmarket.sulsul.member.service;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Member> getMemberList() {

        List<Member> memberList = memberDao.getMemberList();
        if (memberList == null || memberList.isEmpty()) {
            log.error("Not Found Member List : [{}]", memberList);
            throw new NullPointerException("회원 리스트 조회 결과 없습니다.");
        }
        log.debug("member list is select success.");
        return memberList;
    }

    /**
     * 회원 아이디를 받아 정볼르 조회하는 메서드
     */
    public Member getMemberById(String memberId) {

        if (memberId == null) {
            log.error("member id is null not found member.");
            throw new NullPointerException("회원 아이디 값이 없습니다.");
        }

        Member member = memberDao.getMemberById(memberId);
        if(member == null) {
            log.error("not found member by memberId : [{}]", memberId);
            throw new NullPointerException("회원 아이디가 존재하지 않습니다.");
        } else {
            Address address = memberDao.getAddressByMemberId(memberId);
            if (address == null) {
                log.error("member Address is null by memberId : [{}]", memberId);
                throw new IllegalArgumentException("회원 아이디로 주소를 찾을 수가 없습니다.");
            }
            log.debug("find member info : [{}]", member);
            return member;
        }
    }

    /**
     * 아이디 중복 체크 메서드
     */
    public boolean duplicateCheckId(String memberId) {

        if(memberId == null) {
            log.error("memberId is null");
            throw new NullPointerException("아이디 값이 없습니다.");
        } else {
            Member duplicateMember = memberDao.getMemberById(memberId);
            // 있으면 중복
            if(duplicateMember != null) {
                log.info("Is exist member duplicate memberId : [{}]", memberId);
                throw new DuplicateKeyException("이미 존재하는 아이디 입니다.");
            } else {
                log.info("member id is not using : [{}]", memberId);
                return true;
            }
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
            Member duplicateEmail = memberDao.getMemberByEmail(email);
            if(duplicateEmail != null) {
                log.info("IS EXIST DUPLICATE EMAIL -> {}", email);
                throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            }
            log.info("USED EMAIL OK ! ! ! -> {}", email);
        }
    }

    /**
     * 회원 로그인 메서드
     */
    //TODO 소셜 로그인 후 우리 사이트에 필요한 정보를 다시 받기 위한 메서드로 수정 해야함
    // Ex1 -> 네이버 로그인 response 정보를 받아서 추가적인 정보를 여기서 다시 받아가지고
    // 회원 테이블 && 주소 테이블에 적재할 수 있도록 수정
    public void memberLogin(String id, String password) {

        Member loginMember = null;
        // 해당 멤버 아이디로 회원이 있는지 먼저 조회
        loginMember = memberDao.getMemberById(id);

        // FALSE 경우 -> ERROR
        if(loginMember == null) {
            log.error("NOT FOUND MEMBER BY ID");
            throw new NullPointerException("아이디 조회 결과 회원이 없습니다.");
        }

        // 위에서 받은 패스워드랑 아이디로 조회한 회원 정보의 비밀번호를 검사 ! ! !
        if (!passwordEncoder.matches(password, loginMember.getMEMBER_PW())) {
            log.error("INVALID PASSWORD ! ! ! ");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        log.info("MEMBER LOGIN SUCCESS -> {}", loginMember.getMEMBER_ID());
//        return new LoginMember().toDTO(loginMember.getMEMBER_ID(), loginMember.getMEMBER_NAME());
    }

    /**
     * 회원가입 메서드
     */
    @Transactional
    public void memberSign(Member member) {

        if(!duplicateCheckId(member.getMEMBER_ID())){
            return;
        }

        // 이메일 중복 체크
        duplicateCheckEmail(member.getMEMBER_EMAIL());

        // 중복 검증 끝나고 패스워드 암호화
        String password = passwordEncoder.encode(member.getMEMBER_PW());
        log.info("PASSWORD ENCODER -> {}", password);
        // 암호화 한 값을 대입
        member.setMEMBER_PW(password);
        // 위에서 암호화 한 Member 넘겨주면서 회원가입
        Integer insertMember = memberDao.memberSign(member);

        if (insertMember == null || insertMember <= 0) {
            log.error("MEMBER SIGN FAIL ! ! !");
            throw new NullPointerException("회원 가입 실패");
        }
        log.info("MEMBER SIGN SUCCESS ! ! ! -> {}", member.getMEMBER_ID());
    }

    /**
     * 패스워드 변경 메서드
     */
    @Transactional
    public void memberPasswordUpdate(String newPassword, String id) {

        if(newPassword == null) {
            log.error("NULL POINT NEW PASSWORD ! ! !");
            throw new NullPointerException("변경할 비밀번호 값이 없습니다.");
        }

        if(id == null) {
            log.error("NULL POINT MEMBER ID ! ! !");
            throw new NullPointerException("변경할 회원 정보 조회를 실패하였습니다.");
        }

        String encodePassword = passwordEncoder.encode(newPassword);
        Integer updateCount = memberDao.memberPasswordUpdate(encodePassword, id);

        if(updateCount == null || updateCount <= 0) {
            log.info("UPDATE FAIL MEMBER PASSWORD");
            throw new NullPointerException("패스워드 변경에 실패했습니다");
        }
        log.info("UPDATE NEW PASSWORD SUCCESS {}", newPassword);
    }
}