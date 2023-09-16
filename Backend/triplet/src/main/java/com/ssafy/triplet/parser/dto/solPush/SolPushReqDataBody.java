package com.ssafy.triplet.parser.dto.solPush;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolPushReqDataBody {

    @JsonProperty("제휴고객번호")
    private String customerNum;

    @JsonProperty("발송메시지")
    private String sendMessage;
}
