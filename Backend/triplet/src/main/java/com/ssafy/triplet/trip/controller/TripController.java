package com.ssafy.triplet.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.service.TripService;
import com.ssafy.triplet.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class TripController {
	private final TripService tripService;

	@GetMapping
	public ResponseEntity<List<MainPageTripDto>> findAllTrips(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			User user = (User)session.getAttribute("user");
			if (user != null) {
				Long userId = user.getUserId();
				return ResponseEntity.ok(tripService.getAllTrips(userId));
			}
		}
		throw new BaseException(ErrorCode.USER_ID_NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Trip> createTrip(TripDto tripDto) {
		tripService.saveTrip(tripDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{tripId}")
	public ResponseEntity<Trip> deleteTrip(@PathVariable String tripId) {// 메인페이지에 나와있는 프로젝트의 ID를 받아서 삭제
		tripService.removeTrip(Long.parseLong(tripId));
		return ResponseEntity.ok().build();
	}
}
