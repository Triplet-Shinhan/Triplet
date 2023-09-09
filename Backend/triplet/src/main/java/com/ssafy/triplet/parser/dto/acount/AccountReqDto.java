package com.ssafy.triplet.parser.dto.acount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeReqDataBody;
import lombok.Data;

@Data
public class AccountReqDto {

    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private AccountReqDataBody dataBody;
}
