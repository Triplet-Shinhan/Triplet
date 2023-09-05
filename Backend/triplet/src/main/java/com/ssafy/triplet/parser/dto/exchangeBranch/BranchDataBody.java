package com.ssafy.triplet.parser.dto.exchangeBranch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BranchDataBody {
    @JsonProperty("리스트건수")
    private String listCount;

    @JsonProperty("리스트")
    private List<Branch> branchList;
}
