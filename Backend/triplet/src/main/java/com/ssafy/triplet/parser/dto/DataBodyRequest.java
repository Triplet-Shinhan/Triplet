package com.ssafy.triplet.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBodyRequest {

    @JsonProperty("serviceCode")
    private String serviceCode;

    // getters and setters
}
