package com.ssafy.triplet.parser.dto.exchangeRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.exchangeBranch.BranchReqDataBody;
import lombok.Data;

@Data
public class ExchangeRateReqDto {
    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private ExchangeRateReqDataBody dataBody;
}
