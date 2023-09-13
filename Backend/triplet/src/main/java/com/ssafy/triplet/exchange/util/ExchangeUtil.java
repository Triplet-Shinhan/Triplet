package com.ssafy.triplet.exchange.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.triplet.parser.WebClientUtil;
import com.ssafy.triplet.parser.dto.exchangeRate.ExchangeRate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExchangeUtil {
    private final WebClientUtil webClientUtil;

    // 통화코드로 현재 환율을 가지고 오는 메소드
    public Double getExchangeRateByCurrencyCode(String currencyCode) {
        LocalDate now = LocalDate.now();
        List<ExchangeRate> response = webClientUtil
                .getExchangeRate(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        for (ExchangeRate er : response) {
            if (er.getCurrencyCode().trim().equals(currencyCode.trim().toUpperCase())) {
                return Double.parseDouble(er.getExchangeRate());
            }
        }
        return -1.0; // 못찾았으면 -1.0을 반환
    }

    // 두 위도, 경도를 통한 계산
    public Double getDistance(Double userLat, Double userLong, Double branchLat, Double branchLong) {
        Double theta = userLong - branchLong;
        Double distance = Math.sin(deg2rad(userLat)) * Math.sin(deg2rad(branchLat))
                + Math.cos(deg2rad(userLat)) * Math.cos(deg2rad(branchLat)) * Math.cos(deg2rad(theta));

        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        // 미터법
        distance *= 1609.344;
        return distance;
    }

    // 도 -> 라디안
    public Double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    // 라디안 -> 도
    public Double rad2deg(Double rad) {
        return (rad * 180 / Math.PI);
    }
}
