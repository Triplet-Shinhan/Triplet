package com.ssafy.triplet.daily.controller;

import java.util.List;

import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.dto.PaymentDto;
import com.ssafy.triplet.daily.response.DailiesResponse;
import com.ssafy.triplet.daily.response.DailyResponse;
import com.ssafy.triplet.daily.service.DailyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class DailyController {
	private final DailyService dailyService;
	private final UserUtility userUtility;

	@GetMapping("/{tripId}/dailies")
	public ResponseEntity<DailiesResponse> readDailies(@PathVariable Long tripId, HttpServletRequest request){
		User user = userUtility.getUserFromCookie(request);
		List<DailyDto> dailies = dailyService.toDailyDtoList(user,tripId);
		return ResponseEntity.ok(new DailiesResponse(tripId, dailyService.getDashboard(tripId), dailies));
	}

	@GetMapping("/{tripId}/dailies/{dailyId}")
	public ResponseEntity<List<PaymentDto>> readDaily(@PathVariable Long tripId, @PathVariable Long dailyId) {
		List<PaymentDto> payments = dailyService.getPayments(dailyId);
		return ResponseEntity.ok(payments);
	}

	@PostMapping("{tripId}/dailies/{dailyId}/images")
	public ResponseEntity<DailyResponse> saveImage(@PathVariable Long tripId, @PathVariable Long dailyId) { //S3

		return null;
	}
}
