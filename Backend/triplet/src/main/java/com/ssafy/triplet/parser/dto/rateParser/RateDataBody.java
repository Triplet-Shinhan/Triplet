package com.ssafy.triplet.parser.dto.rateParser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class RateDataBody {

    @JsonProperty("리스트건수")
    private String listCount;

    @JsonProperty("리스트")
    private List<CurrencyRate> currencyRate;

}
