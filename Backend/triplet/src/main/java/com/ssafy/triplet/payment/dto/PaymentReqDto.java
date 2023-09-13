package com.ssafy.triplet.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class PaymentReqDto {


    private Long tripId;

    private Long dailyId;

    private String item;

    private Long cost;

    private String foreignCurrency;

    private LocalDateTime date;
}
