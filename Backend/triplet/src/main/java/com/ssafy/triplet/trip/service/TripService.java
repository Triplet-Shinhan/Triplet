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
        LocalDate startDate = tripDto.getStartDate();
        LocalDate endDate = tripDto.getEndDate();
        while (!startDate.isAfter(endDate)) {
            Daily daily = new Daily();
            daily.setUser(user);
            daily.setTrip(trip);
            daily.setDate(startDate);
            daily.setImageUrl(null); // 초기값으로 null 설정
            startDate = startDate.plusDays(1);// 날짜를 하루씩 증가시킴
            dailyRepository.save(daily);
        }
        return trip;
    }


    // 프로젝트 삭제
    public void removeTrip(User loginUser, Long tripId) {
        //유효성 검증
        //프로젝트 ID를 찾을 수 없을 경우 에러
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        Long userId = trip.getUser().getUserId();
        //접근 권한 확인
        if (!loginUser.getUserId().equals(userId)) {
            throw new BaseException(ErrorCode.NOT_AUTHORIZED);
        }
        //삭제
        tripRepository.deleteById(tripId);
    }

    // 프로젝트 수정
    public void editTrip(User loginUser, Long tripId, TripEditDto tripEditDto) {// 여행 시작날짜, 여행 종료날짜, 환율 변경
        //유효성 검증
        tripValidation.checkEditValid(tripEditDto);
        // DB에서 해당 ID의 Trip 찾기
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        Long userId = trip.getUser().getUserId();
        //접근 권한 확인
        if (!loginUser.getUserId().equals(userId)) {
            throw new BaseException(ErrorCode.NOT_AUTHORIZED);
        }
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
        String currency = tripDto.getCurrency();//화폐종류
        String currencyCode = currency.substring(currency.length() - 4, currency.length() - 1);//통화코드만 추출
        trip.setCurrency(currencyCode);
        trip.setFixedRate(exchangeUtil.getExchangeRateByCurrencyCode(currencyCode));//현재 환율 가져오기
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        return trip;
    }
}
