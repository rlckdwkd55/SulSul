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
            log.error("not found member list : [{}]", memberList);
            throw new NullPointerException("회원 리스트 조회 결과 없습니다.");
        }
        log.debug("member list is select success.");
        return memberList;
    }

    /**
      * 회원 아이디를 받아 정보를 조회하는 메서드
     */
    public Member getMemberById(String memberId) throws Exception {

        if (memberId == null) {
            log.error("member id is null not found member.");
            throw new NullPointerException("회원 아이디 값이 없습니다.");
        }

        Member member = memberDao.getMemberById(memberId);
        if(member == null) {
            log.error("not found member by memberId : [{}]", memberId);
            throw new NullPointerException("회원 아이디가 존재하지 않습니다.");
        } else {
            //TODO Exception
            Address address = memberDao.getAddressByMemberId(memberId);
            member.setAddress(address);

            if (member.getAddress() == null) {
                log.error("member Address is null by memberId : [{}]", memberId);
                throw new IllegalArgumentException("회원 아이디로 주소를 찾을 수가 없습니다.");
            }
            log.info("find member info : [{}]", member);
            return member;
        }
    }

    /**
     * 아이디 중복 체크 메서드
     */
    public void duplicateCheckId(String memberId) {

        if(memberId == null) {
            log.error("memberId is null");
            throw new NullPointerException("아이디 값이 없습니다.");
        } else {
            Member duplicateMember = memberDao.getMemberById(memberId);
            // 있으면 중복
            if(duplicateMember != null) {
                log.info("Is exist member duplicate memberId : [{}]", memberId);
                throw new DuplicateKeyException("이미 존재하는 아이디입니다.");
            } else {
                log.info("member id is not using : [{}]", memberId);
            }
        }
    }

    /**
     * 이메일 중복 체크 메서드
     */
    public void duplicateCheckEmail(String email) {

        if(email == null) {
            log.error("memberId is null");
            throw new NullPointerException("이메일 값이 없습니다.");
        } else {
            Member duplicateEmail = memberDao.getMemberByEmail(email);
            if(duplicateEmail != null) {
                log.info("Is exist member duplicate email : [{}]", email);
                throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            } else {
                log.info("member email is not using : [{}]", email);
            }
        }
    }

    /**
     * 회원 로그인 메서드
     * 흠... 계속 틀릴 경우에는 인증 후 새 비밀번호를 변경하는 것도 나쁘지 않은 정책 ..?
     * 추가하면 좋을 것 같음. 2023-08-13 지민
     */
    public void memberLogin(String id, String password) {

        Member loginMember = memberDao.getMemberById(id);

        // 아이디가 없는 경우 로그인 X
        if(loginMember == null) {
            log.error("not found member by memberId");
            throw new NullPointerException("아이디 조회 결과 회원이 없습니다.");
        } else {
            // 위에서 받은 회원의 패스워드랑 아이디로 조회한 회원 정보의 비밀번호를 검사 시 일치하는지 체크
            if (!passwordEncoder.matches(password, loginMember.getMEMBER_PW())) {
                log.error("Invalid password");
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
            } else {
                // 성공의 경우 뭘 return 해야 할지를 아직 정해지지 않아서.. 일단 log
                log.info("member login is success : [{}]", loginMember.getMEMBER_ID());
            }
        }
    }

    /**
     * 회원가입 메서드
     */
    @Transactional
    public void memberSign(Member member) {

        if (member == null) {
            log.error("member sign param is null");
            throw new NullPointerException("회원가입 시 필요한 정보가 없습니다.");
        } else {
            // 아이디 중복 체크
            duplicateCheckId(member.getMEMBER_ID());
            // 이메일 중복 체크
            duplicateCheckEmail(member.getMEMBER_EMAIL());

            // 중복 검증 끝나고 패스워드 암호화
            String password = passwordEncoder.encode(member.getMEMBER_PW());
            log.info("password encode : [{}]", password);
            // 암호화 한 값을 대입
            member.setMEMBER_PW(password);
            // 위에서 암호화 한 Member 넘겨주면서 회원가입
            Integer insertMember = memberDao.memberSign(member);
            if (insertMember == null) {
                log.error("member insert fail : {}", member);
            } else {
                log.info("member sign success. : {}", member);
            }
        }
    }

    /**
     * 패스워드 변경 메서드
     */
    @Transactional
    public void memberPasswordUpdate(String newPassword, String id) {

        if(newPassword == null) {
            log.error("new password is null.");
            throw new NullPointerException("변경할 비밀번호 값이 없습니다.");
        }

        if(id == null) {
            log.error("member id is null.");
            throw new NullPointerException("변경할 회원 정보 조회를 실패하였습니다.");
        }

        String encodePassword = passwordEncoder.encode(newPassword);
        Integer updateCount = memberDao.memberPasswordUpdate(encodePassword, id);

        if(updateCount == null) {
            log.info("new password Update Fail By memberId");
            throw new NullPointerException("패스워드 변경에 실패했습니다");
        }
        log.info("UPDATE NEW PASSWORD SUCCESS {}", newPassword);
    }
}