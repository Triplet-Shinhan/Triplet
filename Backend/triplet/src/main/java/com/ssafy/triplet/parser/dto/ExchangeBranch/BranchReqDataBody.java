package com.ssafy.triplet.parser.dto.ExchangeBranch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchReqDataBody {
    @JsonProperty("serviceCode")
    private String serviceCode;

    @JsonProperty("환전통화")
    private String currency;
}
