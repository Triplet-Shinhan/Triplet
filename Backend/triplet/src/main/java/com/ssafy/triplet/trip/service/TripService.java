package com.ssafy.triplet.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.LoginUserDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {
	private final UserRepository userRepository;
	private final TripRepository tripRepository;

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

	public List<Trip> findAllTrips(LoginUserDto loginUserDto) {
		return tripRepository.findAllByUser_Id(loginUserDto.getUserId());
	}
}
