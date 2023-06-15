package com.sulsulmarket.sulsul.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.web.PortResolverImpl;

@Getter
@Setter
@ToString
public class Address {

    private String MEMBER_ID; //아이디
    private String ADDRESS; //배송지
}
