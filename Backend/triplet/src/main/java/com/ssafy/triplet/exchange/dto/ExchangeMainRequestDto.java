package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 환전 신청 화면에서 환율 정보를 불러오기 위한 request dto
 */
@Data
@RequiredArgsConstructor
public class ExchangeMainRequestDto {
    private final String currency;
}
