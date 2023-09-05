package com.ssafy.triplet.parser.dto.ExchangeBranch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import lombok.Data;

@Data
public class BranchReqDto {
    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private BranchReqDataBody dataBody;
}
