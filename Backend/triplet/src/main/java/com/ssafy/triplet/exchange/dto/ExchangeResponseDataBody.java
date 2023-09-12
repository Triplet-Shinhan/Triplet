package com.ssafy.triplet.exchange.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExchangeResponseDataBody {
    private int ListNum; // 리스트 수
    private List<String> currencyList; // 통화 리스트
    private List<ExchangeData> exchangeData; // 통화별 환율 및 우대율, 최소단위 데이터
}