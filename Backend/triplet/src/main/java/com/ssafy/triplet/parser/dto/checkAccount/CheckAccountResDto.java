package com.ssafy.triplet.parser.dto.checkAccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.currency.CurrencyDataBody;
import lombok.Data;

@Data
public class CheckAccountResDto {

    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private CheckAccount checkAccount;
}
