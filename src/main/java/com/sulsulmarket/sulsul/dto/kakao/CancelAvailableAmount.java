package com.sulsulmarket.sulsul.dto.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CancelAvailableAmount {

    private int total; // 전체 취소 가능 금액
    private int tax_free; // 취소 가능 비과세 금액
}
