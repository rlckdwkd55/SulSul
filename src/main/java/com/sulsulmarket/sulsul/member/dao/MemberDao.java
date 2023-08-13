package com.sulsulmarket.sulsul.member.dao;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao implements MemberMapper {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> getMemberList() {

        return memberMapper.getMemberList();
    }

    @Override
    public Member getMemberById(String id) {

        return memberMapper.getMemberById(id);
    }

    @Override
    public Member memberLogin(String id, String password) {

        return memberMapper.memberLogin(id, password);

    }

    @Override
    public int memberSign(Member Member) {

        return memberMapper.memberSign(Member);
    }

    @Override
    public Member getMemberByEmail(String email) {

        return memberMapper.getMemberByEmail(email);
    }

    @Override
    public int memberPasswordUpdate(String newPassword, String id) {

        return memberMapper.memberPasswordUpdate(newPassword, id);
    }

    @Override
    public Address getAddressByMemberId(String memberId) {
        return memberMapper.getAddressByMemberId(memberId);
    }
}
