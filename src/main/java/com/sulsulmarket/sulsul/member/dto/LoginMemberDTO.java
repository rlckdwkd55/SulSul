package com.sulsulmarket.sulsul.member.dto;

import lombok.Getter;

@Getter
public class LoginMemberDTO {
    private String id;
    private String name;

    public LoginMemberDTO toDTO(String id, String name) {
        this.id = id;
        this.name = name;

        return this;
    }
}
