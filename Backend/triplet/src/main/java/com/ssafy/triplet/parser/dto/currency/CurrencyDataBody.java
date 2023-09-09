package com.ssafy.triplet.parser.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.rateParser.CurrencyRate;
import lombok.Data;

import java.util.List;

@Data
public class CurrencyDataBody {
    @JsonProperty("리스트건수")
    private String listCount;

    @JsonProperty("리스트")
    private List<Currency> currencyList;
}
