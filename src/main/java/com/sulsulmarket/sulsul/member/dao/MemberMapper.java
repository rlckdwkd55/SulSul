package com.sulsulmarket.sulsul.member.dao;

import com.sulsulmarket.sulsul.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<MemberDTO> getMemberList();

    MemberDTO getMemberById(String id);
}
