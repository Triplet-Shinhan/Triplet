package com.ssafy.triplet.parser.dto.checkExchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.exchange.ExchangeDataBody;
import lombok.Data;

@Data
public class CheckExchangeResDto {

    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private CheckExchangeDataBody checkExchangeDataBody;
}
