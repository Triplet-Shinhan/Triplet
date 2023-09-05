package com.ssafy.triplet.exchange.dto;

import lombok.Data;

@Data
public class ExchangeData {
    private String currencyCode; // 통화 코드
    private Double exchangeRate; // 환율
    private Long exchangeUnit; // 최소 단위
    private Integer preferentialRate; // 우대율
}
