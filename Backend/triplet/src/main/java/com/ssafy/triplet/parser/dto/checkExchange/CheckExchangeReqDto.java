package com.ssafy.triplet.parser.dto.checkExchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.exchange.ExchangeReqDataBody;
import lombok.Data;

@Data
public class CheckExchangeReqDto {

    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private CheckExchangeReqDataBody dataBody;
}
