package com.ssafy.triplet.exchange.dto;

import lombok.Data;

@Data
public class ExchangeData {
    private String currencyCode; // 통화 코드
    private String exchangeRate; // 환율
    private String exchangeUnit; // 최소 단위
    private String preferentialRate; // 우대율
}
