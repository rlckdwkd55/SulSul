package com.sulsulmarket.sulsul.member.service;

import com.sulsulmarket.sulsul.Util.SulSulUtil;
import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.member.MemberOne;
import com.sulsulmarket.sulsul.dto.member.SignRequestMember;
import com.sulsulmarket.sulsul.member.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 회원 이메일 받아 정보를 조회하는 메서드
     */
    public MemberOne getMemberById(String email) {

        if (!SulSulUtil.strNullCheck(email)) {
            log.error("member email is null not found member.");
            throw new NullPointerException("파라미터 회원 아이디 값이 없습니다.");
        }

        Member member = memberDao.getMemberByEmail(email);
        if(member == null || Objects.isNull(member)) {
            log.error("not found member by email : [{}]", email);
            throw new NullPointerException("회원 아이디로 조회 결과가 존재하지 않습니다.");
        }
        Address address = memberDao.getAddressByEmail(email);
        if (address == null || Objects.isNull(address)) {
            log.error("member Address is null by email : [{}]", email);
            throw new NullPointerException("회원 아이디로 주소를 찾을 수가 없습니다.");
        }

        MemberOne finalMember = new MemberOne().toDTO(memberDao.getMemberByEmail(email), address);
        log.debug("find member info : [{}]", member);
        return finalMember;
    }

    /**
     * 이메일로 로그인 하기
     */
    public boolean memberLogin(String email) {

        if (!SulSulUtil.strNullCheck(email)) {
            log.error("member email is null not found member.");
            throw new NullPointerException("파라미터 회원 아이디 값이 없습니다.");
        }

        if (memberDao.getMemberByEmail(email) == null) {
            log.error("parameter email is not found member");
            throw new NullPointerException("이메일로 해당 회원을 찾을 수 없습니다.");
        }
        return true;
    }

    /**
     * 이메일 중복 체크 메서드
     */
    public boolean duplicateCheckEmail(String email) {

        if(SulSulUtil.strNullCheck(email)) {
            log.error("member email is null ! ! !");
            throw new NullPointerException("파라미터 이메일 값이 없습니다.");
        } else {
            if(memberDao.getMemberByEmail(email) != null) {
                log.info("is exist duplicate email :: [{}]", email);
                throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            }
            log.info("used email ok. [{}] ", email);
            return true;
        }
    }

    /**
     * 회원가입 메서드
     */
    @Transactional
    public boolean memberSign(SignRequestMember member) {

        //TODO Validation.
        try {
            Integer insertMember = memberDao.memberSign(member);
            member.getAddress().setMEMBER_EMAIL(member.getEmail());
            Integer insertAddress = memberDao.addressSign(member.getAddress());
            if (insertMember == null || insertAddress == null) {
                log.error("member sign is fail..");
                throw new NullPointerException("회원 가입 실패");
            }
        } catch (Exception e) {
            log.error("member Sign DB insert Fail.", e);
            return false;
        }
        return true;
    }
}