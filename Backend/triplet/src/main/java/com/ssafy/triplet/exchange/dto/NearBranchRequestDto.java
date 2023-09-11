package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 환전 화면으로부터 현재위치와 통화 정보를 받아오기 위한 dto
 */

@Data
@RequiredArgsConstructor

public class NearBranchRequestDto {
    private final Double latitude;
    private final Double longitude;
    private final String currency;
}
