package com.sulsulmarket.sulsul.member.dao;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.member.SignRequestAddress;
import com.sulsulmarket.sulsul.dto.member.SignRequestMember;
import com.sulsulmarket.sulsul.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao implements MemberMapper {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int memberSign(SignRequestMember member) {
        return memberMapper.memberSign(member);
    }

    @Override
    public Member getMemberByEmail(String email) {

        return memberMapper.getMemberByEmail(email);
    }

    @Override
    public Address getAddressByEmail(String email) {
        return memberMapper.getAddressByEmail(email);
    }

    @Override
    public int addressSign(Address address) {
        return memberMapper.addressSign(address);
    }
}
