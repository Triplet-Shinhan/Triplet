package com.ssafy.triplet.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.trip.util.TripValidation;
import com.ssafy.triplet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {
	private final UserRepository userRepository;
	private final TripRepository tripRepository;
	private final TripValidation tripValidation;

	// 메인화면 프로젝트 조회
	public List<MainPageTripDto> getAllTrips(Long userId) {
		if (userRepository.findById(userId).orElse(null) == null) {//회원 정보를 찾을 수 없을 경우 에러
			throw new BaseException(ErrorCode.USER_ID_NOT_FOUND);
		}

		List<Trip> trips = tripRepository.findAllByUserUserId(userId);
		List<MainPageTripDto> mainPageTrips = new ArrayList<>();
		for (Trip trip : trips) {
			MainPageTripDto mainPageTripDto = new MainPageTripDto();
			mainPageTripDto.setPrjName(trip.getPrjName());
			mainPageTripDto.setLocation(trip.getLocation());
			mainPageTripDto.setStartDate(trip.getStartDate());
			mainPageTripDto.setEndDate(trip.getEndDate());
			mainPageTrips.add(mainPageTripDto);
		}
		return mainPageTrips;
	}

	// 프로젝트 생성
	public void saveTrip(TripDto tripDto) {
		//유효성 검증
		tripValidation.checkCreateValid(tripDto);
		//생성
		Trip trip = toTripEntity(tripDto);
		tripRepository.save(trip);
	}

	// 프로젝트 삭제
	public void removeTrip(Long tripId) {
		//유효성 검증
		if (tripRepository.findById(tripId).orElse(null) == null) {//프로젝트 ID를 찾을 수 없을 경우 에러
			throw new BaseException(ErrorCode.TRIP_ID_NOT_FOUND);
		}
		//삭제
		tripRepository.deleteById(tripId);
	}

	// 프로젝트 수정
	public void editTrip(Long tripId, TripEditDto tripEditDto) {// 여행 시작날짜, 여행 종료날짜, 환율 변경
		//유효성 검증
		tripValidation.checkEditValid(tripEditDto);
		// DB에서 해당 ID의 Trip 찾기
		Trip trip = tripRepository.findById(tripId).orElse(null);
		if (trip == null) {
			throw new BaseException(ErrorCode.TRIP_ID_NOT_FOUND);
		}
		//수정
		trip.setStartDate(tripEditDto.getStartDate());
		trip.setEndDate(tripEditDto.getStartDate());
		trip.setFixedRate(tripEditDto.getFixedRate());
		tripRepository.save(trip);
	}

	private Trip toTripEntity(TripDto tripDto) {
		Trip trip = new Trip();
		trip.setUser(userRepository.findById(tripDto.getUserId()).orElse(null));
		trip.setPrjName(tripDto.getPrjName());
		trip.setLocation(tripDto.getLocation());
		trip.setBudget(tripDto.getBudget());
		trip.setExchangedBudget(tripDto.getExchangedBudget());
		trip.setUsedBudget(tripDto.getUsedBudget());
		trip.setCurrency(tripDto.getCurrency());
		trip.setFixedRate(tripDto.getFixedRate());
		trip.setStartDate(tripDto.getStartDate());
		trip.setEndDate(tripDto.getEndDate());
		return trip;
	}

}
