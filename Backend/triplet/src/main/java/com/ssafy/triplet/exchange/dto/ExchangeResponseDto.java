package com.ssafy.triplet.exchange.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponseDto {

    private int resultCode;
    private DataBody dataBody;

    @Data
    public static class DataBody {
        private int ListNum;
        private List<String> currencyList;
        private List<ExchangeData> exchangeData;
    }

    @Data
    private static class ExchangeData {

        private Long currencyCode;

        private Float exchangeRate;

        private Long exchangeUnit;

        private String preferentialRate;
    }
}
