package com.ssafy.triplet.parser.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferDataBody {
    @JsonProperty("거래후잔액")
    private String balance;
}
