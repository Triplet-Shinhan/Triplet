package com.ssafy.triplet.parser.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {

    @JsonProperty("통화코드")
    private String currencyCode;

    @JsonProperty("통화코드명")
    private String currencyName;

    @JsonProperty("단위")
    private String exchangeUnit;
}
