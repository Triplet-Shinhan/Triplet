package com.ssafy.triplet.parser.dto.exchangeRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.exchangeBranch.BranchDataBody;
import lombok.Data;

@Data
public class ExchangeRateResDto {
    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private ExchangeRateDataBody dataBody;
}
