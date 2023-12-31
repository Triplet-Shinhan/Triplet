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
import com.ssafy.triplet.user.util.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private final UserUtility userUtility;

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
    public void removeTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

    // 프로젝트 수정
    public void editTrip(Long tripId, TripEditDto tripEditDto, HttpServletRequest request) {// 여행 시작날짜, 여행 종료날짜, 환율 변경
        //유효성 검증
        tripValidation.checkEditValid(tripEditDto.getEndDate().toString());
        // DB에서 해당 ID의 Trip 찾기
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));

        //날짜수정
        LocalDate curEndDate = trip.getEndDate();
        LocalDate changedEndDate = tripEditDto.getEndDate();
        trip.setEndDate(changedEndDate);//보이는 날짜만 수정

        //날짜 비교를 통해 dailies 수정
        // 줄어들 경우
        if (changedEndDate.isBefore(curEndDate)) {
            long gap = ChronoUnit.DAYS.between(changedEndDate, curEndDate);
            //영속성 유지
            List<Daily> dailies = dailyRepository.findAllByTripTripId(trip.getTripId());
            for (int idx = dailies.size() - 1; idx >= dailies.size() - gap; idx--) {
                System.out.println(dailies.get(idx).getDailyId());
                dailyRepository.deleteById(dailies.get(idx).getDailyId());
            }
            return;
        }
        //늘어날 경우
        User user = userUtility.getUserFromCookie(request);
        long gap = ChronoUnit.DAYS.between(curEndDate, changedEndDate);
        while (gap-- > 0) {
            curEndDate = curEndDate.plusDays(1);// 날짜를 하루씩 증가시킴
            Daily daily = new Daily();
            daily.setUser(user);
            daily.setTrip(trip);
            daily.setDate(curEndDate);
            daily.setImageUrl(null); // 초기값으로 null 설정
            dailyRepository.save(daily);
        }
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
