package com.sulsulmarket.sulsul.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Amount {
    private Integer total;
    private Integer tax_free;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;

}
