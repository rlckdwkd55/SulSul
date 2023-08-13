package com.sulsulmarket.sulsul.member.mapper;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> getMemberList();

    Member getMemberById(String id);

    Member memberLogin(String id, String password);

    int memberSign(Member Member);

    Member getMemberByEmail(String email);

    int memberPasswordUpdate(String password, String memberId);

    Address getAddressByMemberId(String memberId);
}
