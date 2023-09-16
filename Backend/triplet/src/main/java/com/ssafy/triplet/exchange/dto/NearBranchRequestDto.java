package com.ssafy.triplet.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 환전 화면으로부터 현재위치와 통화 정보를 받아오기 위한 dto
 */

@Data
@AllArgsConstructor
public class NearBranchRequestDto {
    private Double latitude;
    private Double longitude;
    private String currency;
}
