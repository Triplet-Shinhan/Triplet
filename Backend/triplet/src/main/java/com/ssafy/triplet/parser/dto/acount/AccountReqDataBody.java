package com.ssafy.triplet.parser.dto.acount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountReqDataBody {

    @JsonProperty("계좌번호")
    private String accountNum;
}
