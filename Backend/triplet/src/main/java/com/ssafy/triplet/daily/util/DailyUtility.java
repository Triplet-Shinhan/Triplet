package com.ssafy.triplet.daily.util;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyUtility {
    private final TripRepository tripRepository;
    private final DailyRepository dailyRepository;

    public Long getDailyExpenditure(Daily daily) {//일일 총지출
        Long sum = 0L;
        List<Payment> payments = daily.getPayments();
        if (payments != null) {
            for (Payment payment : payments) {
                sum += payment.getCost();
            }
        }
        return sum;
    }

    public Long getDailiesExpenditure(Long tripId) {//여행 총지출
        List<Daily> dailies = dailyRepository.findAllByTripTripId(tripId);
        Long sumExpenditure = 0L;
        for (Daily daily : dailies) {
            sumExpenditure += getDailyExpenditure(daily);
        }
        return sumExpenditure;
    }

    public Long getDailiesCashLeft(Long tripId) {//여행 남은 현금
        List<Daily> dailies = dailyRepository.findAllByTripTripId(tripId);
        Long usedCash = 0L;
        for (Daily daily : dailies) {
            List<Payment> payments = daily.getPayments();
            for (Payment payment : payments) {
                if (payment.getMethod().equals("cash")) {
                    usedCash += payment.getCost();
                }
            }
        }
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        return trip.getExchangedBudget() - usedCash;
    }
}
