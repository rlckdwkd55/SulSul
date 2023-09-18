package com.sulsulmarket.sulsul.ennumurate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRoleType {

    BASIC("Basic", "비회원 권한"),
    ACTIVE("Active", "회원 권한"),
    ADMIN("Admin", "관리자 권한");

    private final String role;
    private final String description;
}
