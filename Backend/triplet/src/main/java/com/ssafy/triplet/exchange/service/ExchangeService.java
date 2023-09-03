package com.ssafy.triplet.exchange.service;

import com.ssafy.triplet.exchange.dto.ExchangeDto;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    public ExchangeDto getRate(String currency){

        //신한 api 요청 해서 통화코드, 환율 , 환전신청단위 , 우대율

//
//        return  ExchangeDto.builder()
//                .build()
//                .setRate(181.58f)

    return new ExchangeDto();
    }
}
