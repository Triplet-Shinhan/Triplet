package com.ssafy.triplet.exchange.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 신한 API로부터 요청한 환율 정보를 가져오는 response dto
 */

@Data
@RequiredArgsConstructor
public class RateByCountryResponseDto {

    private final DataHeader dataHeader;
    private final DataBody dataBody;

    @Data
    public static class DataHeader {
        private String successCode;
        private String resultCode;
        private String resultMessage;
    }

    @Data
    public static class DataBody {
        @JsonAlias("리스트건수")
        private String listNum;

        @JsonAlias("리스트")
        private List<CurrencyInfo> dataList;

        @Data
        public static class CurrencyInfo {
            @JsonAlias("통화코드")
            private String currencyCode;

            @JsonAlias("통화코드명")
            private String currencyName;

            @JsonAlias("단위")
            private String unit;
        }
    }
}
