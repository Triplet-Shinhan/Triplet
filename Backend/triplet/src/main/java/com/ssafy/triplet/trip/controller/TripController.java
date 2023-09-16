package com.ssafy.triplet.trip.controller;

import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;
import com.ssafy.triplet.trip.response.TripResponse;
import com.ssafy.triplet.trip.service.TripService;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;
import com.ssafy.triplet.user.util.UserValidation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class TripController {
    private final TripService tripService;
    private final UserUtility userUtility;
    private final UserValidation userValidation;

    @GetMapping
    public ResponseEntity<List<MainPageTripDto>> readTrips(HttpServletRequest request) {
        User loginUser = userUtility.getUserFromCookie(request);//검증
        return ResponseEntity.ok(tripService.getAllTrips(loginUser.getUserId()));
    }

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripDto tripDto,
                                                   HttpServletRequest request) {
        User loginUser = userUtility.getUserFromCookie(request);//검증
        Trip savedTrip = tripService.saveTrip(tripDto, loginUser);
        return ResponseEntity.ok(new TripResponse(savedTrip.getTripId()));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<TripResponse> deleteTrip(@PathVariable Long tripId, HttpServletRequest request) {
        userValidation.checkTripValid(tripId, request);//검증
        tripService.removeTrip(tripId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<TripResponse> updateTrip(@PathVariable Long tripId, @RequestBody TripEditDto tripEditDto, HttpServletRequest request) {
        userValidation.checkTripValid(tripId, request);//검증
        tripService.editTrip(tripId, tripEditDto, request);
        return ResponseEntity.ok(new TripResponse(tripId));
    }
}
