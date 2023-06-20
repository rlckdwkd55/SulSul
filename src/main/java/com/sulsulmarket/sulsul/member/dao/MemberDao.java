package com.sulsulmarket.sulsul.member.dao;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao implements MemberMapper {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<MemberDTO> getMemberList() {

        return memberMapper.getMemberList();
    }

    @Override
    public MemberDTO getMemberById(String id) {

        return memberMapper.getMemberById(id);
    }

    @Override
    public MemberDTO memberLogin(String id, String password) {

        return memberMapper.memberLogin(id, password);

    }

    @Override
    public int memberSign(MemberDTO memberDTO) {

        return memberMapper.memberSign(memberDTO);
    }

    @Override
    public MemberDTO getMemberByEmail(String email) {

        return memberMapper.getMemberByEmail(email);
    }

    @Override
    public int memberPasswordUpdate(String newPassword, String id) {

        return memberMapper.memberPasswordUpdate(newPassword, id);
    }
}
