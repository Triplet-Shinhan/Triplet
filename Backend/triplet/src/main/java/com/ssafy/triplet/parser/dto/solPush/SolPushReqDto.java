package com.ssafy.triplet.parser.dto.solPush;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.transfer.TransferReqDataBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolPushReqDto {
    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private SolPushReqDataBody dataBody;
}
