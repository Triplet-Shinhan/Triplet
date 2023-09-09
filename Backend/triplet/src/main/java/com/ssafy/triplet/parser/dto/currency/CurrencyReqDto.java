package com.ssafy.triplet.parser.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataBodyRequest;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import lombok.Data;

@Data
public class CurrencyReqDto {
    @JsonProperty("dataHeader")
    private DataHeaderRequest dataHeader;

    @JsonProperty("dataBody")
    private DataBodyRequest dataBody;

}
