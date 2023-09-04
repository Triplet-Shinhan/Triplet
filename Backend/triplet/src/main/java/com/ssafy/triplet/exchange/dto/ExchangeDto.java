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
public class ExchangeDto {

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
        @JsonAlias("통화코드")
        private Long currencyCode;

        @JsonAlias("환율")
        private Float exchangeRate;

        @JsonAlias("환전신청단위")
        private Long exchangeUnit;

        @JsonAlias("우대율")
        private String preferentialRate;
    }
}
