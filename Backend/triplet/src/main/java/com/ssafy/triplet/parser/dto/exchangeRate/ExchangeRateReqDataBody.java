package com.ssafy.triplet.parser.dto.exchangeRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExchangeRateReqDataBody {

    @JsonProperty("조회일자")
    String findDate;
}
