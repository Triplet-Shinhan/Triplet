package com.ssafy.triplet.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeApplyRequestDto {
    private String currency;

    private Long amount;

    private String location;

    private String receiptDate;

    private Integer receiveWay;
}
