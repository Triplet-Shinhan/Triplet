package com.ssafy.triplet.exchange.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.exchange.dto.ExchangeApplyRequestDto;
import com.ssafy.triplet.exchange.dto.ExchangeApplyResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResultsResponseDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;

@Service
public class ExchangeService {

    public ExchangeResponseDto getRate(String currency) {

        // 신한 api 요청 해서 통화코드, 환율 , 환전신청단위 , 우대율

        ExchangeResponseDto exchangeResponseDto = new ExchangeResponseDto();
        // 신한 api로 부터 데이터를 가지고 온다.
        List<CurrencyResponse> cr = API();
        List<String> currencyList;

        for (CurrencyResponse currency : cr) {
            currencyList.add(currency.currencyCode);

        }
        // 정제를 수행

        exchangeResponseDto.setDataBody();
        return new ExchangeResponseDto();
    }

    public NearBranchResponseDto getNearBranch(Double latitude, Double longtitude) {
        return new NearBranchResponseDto();
    }

    public ExchangeResultsResponseDto getExchangeResults() {
        return new ExchangeResultsResponseDto();
    }

    public ExchangeApplyResponseDto applyExchange(ExchangeApplyRequestDto exchangeApplyRequestDto) {
        return new ExchangeApplyResponseDto();
    }
}
