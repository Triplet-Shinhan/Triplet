package com.ssafy.triplet.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {
	private final UserRepository userRepository;
	private final TripRepository tripRepository;

	//메인화면 프로젝트 조회
	public List<MainPageTripDto> getAllTrips(Long userId) {
		List<Trip> trips = tripRepository.findAllByUserId(userId);
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

	//프로젝트 생성
	public void saveTrip(TripDto tripDto) {
		Trip trip = toTripEntity(tripDto);
		tripRepository.save(trip);
	}

	public void removeTrip(Long tripId) {
		//유효성 검증

		//삭제
		tripRepository.deleteById(tripId);
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
		trip.setFixedBudget(tripDto.getFixedBudget());
		trip.setStartDate(tripDto.getStartDate());
		trip.setEndDate(tripDto.getEndDate());
		return trip;
	}

}
