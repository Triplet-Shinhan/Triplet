package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeResultsRequestDto {
    private String serviceCode;
    private String name;
    private String phoneNum;
    private String birth;
}
