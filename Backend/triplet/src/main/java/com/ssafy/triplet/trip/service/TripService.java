package com.ssafy.triplet.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.repository.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {
	private final TripRepository tripRepository;

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
}
