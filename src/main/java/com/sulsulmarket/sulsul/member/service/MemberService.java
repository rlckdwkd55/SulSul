package com.sulsulmarket.sulsul.member.service;

import com.sulsulmarket.sulsul.member.dao.MemberMapper;
import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MemberService implements MemberMapper {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<MemberDTO> getMemberList() {

        List<MemberDTO> memberList = memberMapper.getMemberList();

            if(Objects.isNull(memberList)) {
                log.error("ERROR -> Not Found Member List ! ! !");
            }


        return memberList;
    }

    @Override
    public MemberDTO getMemberById(String id) {

        MemberDTO memberDTO = memberMapper.getMemberById(id);

        if(Objects.isNull(memberDTO)) {
            log.error("ERROR -> Not Found Member By Id");
        }

        return memberDTO;
    }
}
