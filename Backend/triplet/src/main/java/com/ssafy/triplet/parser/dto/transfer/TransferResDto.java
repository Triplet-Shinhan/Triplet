package com.ssafy.triplet.parser.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.rateParser.RateDataBody;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TransferResDto {
    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private TransferDataBody transferDataBody;

}
