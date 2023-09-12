package com.ssafy.triplet.exchange.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class ExchangeCodeData {

    @JsonAlias("통화코드")
    private String currencyCode;

    @JsonAlias("통화코드명")
    private String currencyName;

    @JsonAlias("단위")
    private Long unit;

}
