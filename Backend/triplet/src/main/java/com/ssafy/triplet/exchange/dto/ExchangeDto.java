package com.ssafy.triplet.exchange.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto {
    private long currency;

    private float rate;

    private Long unit;

    private String benefitRate;


}
