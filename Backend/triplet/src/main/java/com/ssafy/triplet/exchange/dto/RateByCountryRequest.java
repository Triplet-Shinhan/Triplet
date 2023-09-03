package com.ssafy.triplet.exchange.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RateByCountryRequest {
    private final DataHeader dataHeader;
    private final DataBody DataBody;

    public RateByCountryRequest(String apikey, String serviceCode) {
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
