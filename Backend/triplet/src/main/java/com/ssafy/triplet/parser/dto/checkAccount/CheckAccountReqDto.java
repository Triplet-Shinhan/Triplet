package com.ssafy.triplet.parser.dto.checkAccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.currency.CurrencyDataBody;
import lombok.Data;

@Data
public class CheckAccountReqDto {
    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private AuthRequestDto dataBody;
}
