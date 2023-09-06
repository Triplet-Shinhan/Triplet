package com.ssafy.triplet.parser.dto.checkAccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthRequestDto {
    @JsonProperty("입금은행코드")
    private String bankCode;

    @JsonProperty("입금계좌번호")
    private String accountNum;

    @JsonProperty("입금통장메모")
    private String memo;
}
