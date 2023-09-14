package com.ssafy.triplet.daily.service;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.dto.DashboardDto;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.daily.util.DailyUtility;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyService {
    private final TripRepository tripRepository;
    private final DailyRepository dailyRepository;
    private final DailyUtility dailyUtility;

    public DashboardDto getDashboard(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        DashboardDto dashboardDto = new DashboardDto();

        Long sumExpenditure = dailyUtility.getDailiesExpenditure(tripId);
        dashboardDto.setSumExpenditure(sumExpenditure);
        dashboardDto.setBudget(trip.getBudget() - sumExpenditure);
        dashboardDto.setCash(dailyUtility.getDailiesCashLeft(tripId));
        return dashboardDto;
    }

    public List<DailyDto> toDailyDtoList(Long tripId) {
        List<Daily> dailies = dailyRepository.findAllByTripTripId(tripId);
        //dailies가 null일 경우 유효성 검증할지 고민해보기
        List<DailyDto> dtoList = new ArrayList<>();
        for (Daily daily : dailies) {
            DailyDto dailyDto = new DailyDto();
            dailyDto.setDailyId(daily.getDailyId());
            dailyDto.setDate(daily.getDate());
            dailyDto.setImageUrl(null);//S3 활용하기
            dailyDto.setSum(dailyUtility.getDailyExpenditure(daily));
            dtoList.add(dailyDto);
        }
        return dtoList;
    }

    public List<Payment> getPayments(Long dailyId) {
        Daily daily = dailyRepository.findById(dailyId)
                .orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
        return new ArrayList<>(daily.getPayments());//지연로딩 이슈 해결
    }
}
