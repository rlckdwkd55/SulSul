package com.sulsulmarket.sulsul.member.dao;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import com.sulsulmarket.sulsul.member.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao implements MemberMapper {

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
}
