package com.sulsulmarket.sulsul.dto.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CanceledAmount {

    private int total; // 취소된 전체 누적 금액
    private int tax_free; // 취소된 비과세 누적 금액
}
