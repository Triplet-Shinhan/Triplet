package com.ssafy.triplet.daily.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.daily.response.DailyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class DailyController {
	@GetMapping("/{tripId}/dailies")
	public ResponseEntity<DailyResponse> readDailies(@PathVariable Long tripId) {
		//기록조회때마다 총지출 합산 로직 필요
		return null;
	}

	@GetMapping("/{tripId}/dailies/{dailyId}")
	public ResponseEntity<DailyResponse> readDaily(@PathVariable Long tripId, @PathVariable Long dailyId) {
		return null;
	}

	@PostMapping("{tripId}/dailies/{dailyId}/images")
	public ResponseEntity<DailyResponse> saveImage(@PathVariable Long tripId, @PathVariable Long dailyId) {
		return null;
	}
}
