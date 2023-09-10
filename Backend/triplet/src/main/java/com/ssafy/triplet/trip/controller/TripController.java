package com.ssafy.triplet.trip.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;
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
	private final Logger logger = LoggerFactory.getLogger(TripController.class);

	@GetMapping
	public ResponseEntity<List<MainPageTripDto>> readTrips(HttpServletRequest request) {
		HttpSession session = request.getSession();
		logger.debug("readTrips request success");
		if (session != null) {
			User user = (User)session.getAttribute("user");
			if (user != null) {
				Long userId = user.getUserId();
				logger.debug("readTrips success");
				return ResponseEntity.ok(tripService.getAllTrips(userId));
			}
		}
		throw new BaseException(ErrorCode.USER_ID_NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Trip> createTrip(@RequestBody TripDto tripDto) {
		logger.debug("createTrip request success");
		tripService.saveTrip(tripDto);
		logger.debug("createTrip success");
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{tripId}")
	public ResponseEntity<Trip> deleteTrip(@PathVariable Long tripId) {
		logger.debug("deleteTrip request success");
		tripService.removeTrip(tripId);
		logger.debug("deleteTrip success");
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{tripId}")
	public ResponseEntity<Trip> updateTrip(@PathVariable Long tripId, @RequestBody TripEditDto tripEditDto) {
		logger.debug("editTrip request success");
		tripService.editTrip(tripId, tripEditDto);
		logger.debug("editTrip success");
		return ResponseEntity.ok().build();
	}

	// @GetMapping("/{tripId}/dailies")
	// public ResponseEntity<List<Daily>> readDaily(@PathVariable Long tripId) {
	// 	return null;
	// }
	//
	// @GetMapping("{tripId}/dailies/{dailyId}")
	// public ResponseEntity<List<Payment>> readPayment(@PathVariable Long tripId, @PathVariable Long dailyId) {
	// 	return null;
	// }
	//
	// @PostMapping("{tripId}/dailies/{dailyId}/images")
	// public ResponseEntity<Trip> uploadImage(@PathVariable Long tripId, @PathVariable Long dailyId) {
	// 	return null;
	// }
}
