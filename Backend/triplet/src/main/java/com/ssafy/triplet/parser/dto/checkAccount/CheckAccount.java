package com.ssafy.triplet.parser.dto.checkAccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckAccount {

    @JsonProperty("입금은행코드")
    private String bankCode;

    @JsonProperty("입금계좌번호")
    private String accountNum;
}
