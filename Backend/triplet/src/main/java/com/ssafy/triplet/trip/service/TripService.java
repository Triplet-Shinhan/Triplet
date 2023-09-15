package com.ssafy.triplet.trip.service;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.exchange.util.ExchangeUtil;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.trip.util.TripValidation;
import com.ssafy.triplet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripValidation tripValidation;
    private final DailyRepository dailyRepository;
    private final ExchangeUtil exchangeUtil;

    // 메인화면 프로젝트 조회
    public List<MainPageTripDto> getAllTrips(Long userId) {
        List<Trip> trips = tripRepository.findAllByUserUserId(userId);
        List<MainPageTripDto> mainPageTrips = new ArrayList<>();
        for (Trip trip : trips) {
            MainPageTripDto mainPageTripDto = new MainPageTripDto();
            mainPageTripDto.setTripId(trip.getTripId());
            mainPageTripDto.setPrjName(trip.getPrjName());
            mainPageTripDto.setLocation(trip.getLocation());
            mainPageTripDto.setStartDate(trip.getStartDate());
            mainPageTripDto.setEndDate(trip.getEndDate());
            mainPageTrips.add(mainPageTripDto);
        }
        return mainPageTrips;
    }

    // 프로젝트 생성
    public Trip saveTrip(TripDto tripDto, User user) {
        //유효성 검증
        tripValidation.checkCreateValid(tripDto);
        //여행 생성
        Trip trip = toTripEntity(tripDto, user);
        tripRepository.save(trip);
        //일일 생성
        LocalDate startDate = tripDto.getStartDate().toLocalDate();
        LocalDate endDate = tripDto.getEndDate().toLocalDate();
        while (!startDate.isAfter(endDate)) {
            startDate = startDate.plusDays(1);// 날짜를 하루씩 증가시킴
            Daily daily = new Daily();
            daily.setUser(user);
            daily.setTrip(trip);
            daily.setDate(java.sql.Date.valueOf(startDate)); // LocalDate를 java.sql.Date로 변환
            daily.setImageUrl(null); // 초기값으로 null 설정
            dailyRepository.save(daily);
        }
        return trip;
    }

    // 프로젝트 삭제
    public void removeTrip(Long tripId) {
        //유효성 검증
        //프로젝트 ID를 찾을 수 없을 경우 에러
        tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        //삭제
        tripRepository.deleteById(tripId);
    }

    // 프로젝트 수정
    public void editTrip(Long tripId, TripEditDto tripEditDto) {// 여행 시작날짜, 여행 종료날짜, 환율 변경
        //유효성 검증
        tripValidation.checkEditValid(tripEditDto);
        // DB에서 해당 ID의 Trip 찾기
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        //수정
        trip.setStartDate(tripEditDto.getStartDate());
        trip.setEndDate(tripEditDto.getEndDate());
        tripRepository.save(trip);
    }

    private Trip toTripEntity(TripDto tripDto, User loginUser) {
        Trip trip = new Trip();
        trip.setUser(loginUser);
        trip.setPrjName(tripDto.getPrjName());
        trip.setLocation(tripDto.getLocation());
        trip.setBudget(tripDto.getBudget());
        trip.setExchangedBudget(tripDto.getExchangedBudget());
        trip.setUsedBudget(0L);
        String currency = tripDto.getCurrency().substring(0, 3);//환율
        trip.setCurrency(currency);
        trip.setFixedRate(exchangeUtil.getExchangeRateByCurrencyCode(currency));//현재 환율 가져오기
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        return trip;
    }
}
