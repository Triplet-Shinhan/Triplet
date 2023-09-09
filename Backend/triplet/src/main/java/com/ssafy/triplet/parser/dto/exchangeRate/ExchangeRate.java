package com.ssafy.triplet.parser.dto.exchangeRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExchangeRate {

    @JsonProperty("통화CODE")
    private String currencyCode;

    @JsonProperty("통화CODE_DISPLAY")
    private String currencyDisplay;

    @JsonProperty("지폐매도환율")
    private String exchangeRate;


}
