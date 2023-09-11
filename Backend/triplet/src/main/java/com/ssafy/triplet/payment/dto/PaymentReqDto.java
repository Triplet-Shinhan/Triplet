package com.ssafy.triplet.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class PaymentReqDto {


    private long tripId;

    private long dailyId;

    private String item;

    private long cost;

    private String foreignCurrency;

    private LocalDateTime date;
}
