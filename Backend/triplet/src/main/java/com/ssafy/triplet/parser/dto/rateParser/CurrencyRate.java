package com.ssafy.triplet.parser.dto.rateParser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyRate {

    @JsonProperty("순서")
    private String order;

    @JsonProperty("통화")
    private String currencyCode;

    @JsonProperty("우대율")
    private String preferentialRate;

}
