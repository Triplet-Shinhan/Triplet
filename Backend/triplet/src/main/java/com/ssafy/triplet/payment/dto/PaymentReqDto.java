package com.ssafy.triplet.payment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PaymentReqDto {

    private long userId;

    private long tripId;

    private long dailyId;

    private String item;

    private long cost;

    private String foreignCurrency;

    private LocalDateTime date;
}
