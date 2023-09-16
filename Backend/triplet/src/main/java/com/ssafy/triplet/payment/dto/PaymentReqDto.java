package com.ssafy.triplet.payment.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReqDto {

    @JsonProperty("tripId")
    private Long tripId;

    @JsonProperty("dailyId")
    private Long dailyId;

    @JsonProperty("item")
    private String item;

    @JsonProperty("cost")
    private Long cost;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime date;
}
