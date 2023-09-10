package com.sulsulmarket.sulsul.dto.member;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberOne {
    private String name;
    private String email;
    private String gender;
    private String address;
    private String phone;

    public MemberOne toDTO(Member member, Address address) {
        this.name = member.getMEMBER_NAME();
        this.email = member.getMEMBER_EMAIL();
        this.gender = member.getMEMBER_GENDER();
        this.address = address.getADDRESS();
        this.phone = member.getMEMBER_PHONE();

        return this;
    }
}
