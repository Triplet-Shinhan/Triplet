package com.ssafy.triplet.trip.controller;

import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;
import com.ssafy.triplet.trip.response.TripResponse;
import com.ssafy.triplet.trip.service.TripService;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(TripController.class);

    @GetMapping
    public ResponseEntity<List<MainPageTripDto>> readTrips(HttpServletRequest request) {
        logger.debug("readTrips request success");
        User loginUser = userUtility.getUserFromCookie(request);
        return ResponseEntity.ok(tripService.getAllTrips(loginUser.getUserId()));
    }

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripDto tripDto,
                                                   HttpServletRequest request) {
        logger.debug("createTrip request success");
        User loginUser = userUtility.getUserFromCookie(request);
        Trip savedTrip = tripService.saveTrip(tripDto, loginUser);
        logger.debug("createTrip success");
        return ResponseEntity.ok(new TripResponse(savedTrip.getTripId()));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<TripResponse> deleteTrip(@PathVariable Long tripId, HttpServletRequest request) {
        logger.debug("deleteTrip request success");
        User loginUser = userUtility.getUserFromCookie(request);
        tripService.removeTrip(loginUser, tripId);
        logger.debug("deleteTrip success");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<TripResponse> updateTrip(@PathVariable Long tripId, @RequestBody TripEditDto tripEditDto, HttpServletRequest request) {
        logger.debug("editTrip request success");
        User loginUser = userUtility.getUserFromCookie(request);
        tripService.editTrip(loginUser, tripId, tripEditDto);
        logger.debug("editTrip success");
        return ResponseEntity.ok(new TripResponse(tripId));
    }
}
