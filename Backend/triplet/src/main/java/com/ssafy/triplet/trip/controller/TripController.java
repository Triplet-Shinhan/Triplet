package com.ssafy.triplet.trip.controller;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.dto.MainPageTripDto;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;
import com.ssafy.triplet.trip.response.TripResponse;
import com.ssafy.triplet.trip.service.TripService;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.repository.UserRepository;
import com.ssafy.triplet.user.util.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class TripController {
    private final UserRepository userRepository;
    private final TripService tripService;
    private final UserUtility userUtility;
    private final Logger logger = LoggerFactory.getLogger(TripController.class);

    @GetMapping
    public ResponseEntity<List<MainPageTripDto>> readTrips(HttpServletRequest request) {
        HttpSession session = request.getSession();
        logger.debug("readTrips request success");
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Long userId = user.getUserId();
                logger.debug("readTrips success");
                //회원 정보를 찾을 수 없을 경우 에러
                userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.USER_ID_NOT_FOUND));
                return ResponseEntity.ok(tripService.getAllTrips(userId));
            }
        }
        throw new BaseException(ErrorCode.USER_ID_NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripDto tripDto, HttpServletRequest httpServletRequest) {
        logger.debug("createTrip request success");
        User loginUser = userUtility.getUserFromCookie(httpServletRequest);
        Trip savedTrip = tripService.saveTrip(tripDto, loginUser);
        logger.debug("createTrip success");
        return ResponseEntity.ok(new TripResponse(savedTrip.getTripId()));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<TripResponse> deleteTrip(@PathVariable Long tripId) {
        logger.debug("deleteTrip request success");
        tripService.removeTrip(tripId);
        logger.debug("deleteTrip success");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<TripResponse> updateTrip(@PathVariable Long tripId, @RequestBody TripEditDto tripEditDto) {
        logger.debug("editTrip request success");
        tripService.editTrip(tripId, tripEditDto);
        logger.debug("editTrip success");
        return ResponseEntity.ok(new TripResponse(tripId));
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
