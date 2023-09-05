package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeApplyResponseDto {
    private Integer resultCode;

    private Long convertedKRWAmount;

    private Double exchangeRate;

    private Long preferentialRate;
}