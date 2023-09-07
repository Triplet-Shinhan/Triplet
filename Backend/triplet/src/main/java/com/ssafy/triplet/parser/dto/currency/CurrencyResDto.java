package com.ssafy.triplet.parser.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.rateParser.RateDataBody;
import lombok.Data;

@Data
public class CurrencyResDto {
    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private CurrencyDataBody currencyDataBody;
}
