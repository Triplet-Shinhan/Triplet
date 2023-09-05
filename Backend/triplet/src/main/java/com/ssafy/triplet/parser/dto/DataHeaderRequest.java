package com.ssafy.triplet.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataHeaderRequest {

    @JsonProperty("apikey")
    private String apikey;

    // getters and setters
}
