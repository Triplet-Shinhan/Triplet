package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeApplyResponseDto {
    private String resultCode;

    private String convertedKRWAmount;

    private String exchangeRate;

    private String preferentialRate;
}