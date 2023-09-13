package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 신한 API로부터 환율 정보를 요청하는 데이터
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RateByCountryRequestDto {
    private final DataHeader dataHeader;
    private final DataBody DataBody;

    public RateByCountryRequestDto(String apikey, String serviceCode) {
        dataHeader = new DataHeader(apikey);
        DataBody = new DataBody(serviceCode);
    }

    @Data
    public static class DataHeader {
        private final String apikey;
    }

    @Data
    public static class DataBody {
        private final String serviceCode;
    }
}
