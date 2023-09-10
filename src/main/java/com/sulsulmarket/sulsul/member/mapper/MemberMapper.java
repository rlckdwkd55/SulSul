package com.sulsulmarket.sulsul.member.mapper;

import com.sulsulmarket.sulsul.dto.member.Address;
import com.sulsulmarket.sulsul.dto.member.Member;
import com.sulsulmarket.sulsul.dto.member.SignRequestAddress;
import com.sulsulmarket.sulsul.dto.member.SignRequestMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int memberSign(SignRequestMember member);

    Member getMemberByEmail(String email);

    Address getAddressByEmail(String email);

    int addressSign(Address address);
}
