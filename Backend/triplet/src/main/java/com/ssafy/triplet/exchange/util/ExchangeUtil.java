package com.ssafy.triplet.exchange.util;

import org.springframework.stereotype.Component;

import com.ssafy.triplet.exchange.dto.NearBranchRequestDto;

@Component
public class ExchangeUtil {
    public Double getDistance(NearBranchRequestDto dto, Double latitude, Double longitude) {
        Double distance = 0.0;
        distance = Math.sqrt((dto.getLatitude() - latitude) * (dto.getLatitude() - latitude)
                + (dto.getLongitude() - longitude) * (dto.getLongitude() - longitude));
        return distance;
    }
}
