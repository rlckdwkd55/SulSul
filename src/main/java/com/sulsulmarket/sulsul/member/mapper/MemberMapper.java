package com.sulsulmarket.sulsul.member.mapper;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberDTO> getMemberList();

    MemberDTO getMemberById(String id);

    MemberDTO memberLogin(String id, String password);

    int memberSign(MemberDTO memberDTO);
}
