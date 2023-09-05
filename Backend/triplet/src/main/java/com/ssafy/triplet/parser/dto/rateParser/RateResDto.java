package com.ssafy.triplet.parser.dto.rateParser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.DataHeader;
import lombok.Data;

@Data
public class RateResDto {
    @JsonProperty("dataHeader")
    private DataHeader dataHeader;

    @JsonProperty("dataBody")
    private RateDataBody rateDataBody;

}

