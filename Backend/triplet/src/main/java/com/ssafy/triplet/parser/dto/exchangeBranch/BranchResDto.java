package com.ssafy.triplet.parser.dto.exchangeBranch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import lombok.Data;

@Data
public class BranchResDto {
    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private BranchDataBody dataBody;
}
