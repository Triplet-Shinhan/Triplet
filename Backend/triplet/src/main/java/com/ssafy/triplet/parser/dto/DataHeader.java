package com.ssafy.triplet.parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataHeader {

    @JsonProperty("successCode")
    private String successCode;

    @JsonProperty("resultCode")
    private String resultCode;

    @JsonProperty("resultMessage")
    private String resultMessage;

    // getters and setters
}
