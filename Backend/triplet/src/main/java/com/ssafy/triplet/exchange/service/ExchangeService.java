package com.ssafy.triplet.exchange.service;

import com.ssafy.triplet.exchange.dto.ExchangeApplyRequestDto;
import com.ssafy.triplet.exchange.dto.ExchangeApplyResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResultsRequestDto;
import com.ssafy.triplet.exchange.dto.ExchangeResultsResponseDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;

import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    public ExchangeResponseDto getRate(String currency) {

        // 신한 api 요청 해서 통화코드, 환율 , 환전신청단위 , 우대율

        //
        // return ExchangeDto.builder()
        // .build()
        // .setRate(181.58f)

        return new ExchangeResponseDto();
    }

    public NearBranchResponseDto getNearBranch(Double latitude, Double longtitude) {
        return new NearBranchResponseDto();
    }

    public ExchangeResultsResponseDto getExchangeResults(ExchangeResultsRequestDto exchangeResultsRequestDto) {
        return new ExchangeResultsResponseDto();
    }

    public ExchangeApplyResponseDto applyExchange(ExchangeApplyRequestDto exchangeApplyRequestDto) {
        return new ExchangeApplyResponseDto();
    }
}
