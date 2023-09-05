package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRateDataBody {
    private String currency;

    private String currencyDisplay;

    private Double sellingRate;
}
