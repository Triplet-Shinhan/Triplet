package com.ssafy.triplet.parser.dto.acount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeDataBody;
import lombok.Data;

@Data
public class AccountResDto
{
    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private AccountDataBody accountDataBody;
}
