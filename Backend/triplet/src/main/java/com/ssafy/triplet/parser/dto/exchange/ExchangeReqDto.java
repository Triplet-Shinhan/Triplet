package com.ssafy.triplet.parser.dto.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import lombok.Data;

@Data
public class ExchangeReqDto {

    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private ExchangeReqDataBody dataBody;
}
