package com.ssafy.triplet.parser.dto.rateParser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RateList {

    @JsonProperty("순서")
    private String order;

    @JsonProperty("통화")
    private String currency;

    @JsonProperty("우대율")
    private String rate;

}
