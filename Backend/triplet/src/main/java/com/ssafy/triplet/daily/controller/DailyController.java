package com.ssafy.triplet.daily.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.dto.PaymentDto;
import com.ssafy.triplet.daily.response.DailiesResponse;
import com.ssafy.triplet.daily.response.DailyResponse;
import com.ssafy.triplet.daily.service.DailyService;
import com.ssafy.triplet.daily.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class DailyController {
	private final DailyService dailyService;
	private final S3Service s3Service;

	@GetMapping("/{tripId}/dailies")
	public ResponseEntity<DailiesResponse> readDailies(@PathVariable Long tripId) {
		List<DailyDto> dailies = dailyService.toDailyDtoList(tripId);
		return ResponseEntity.ok(new DailiesResponse(tripId, dailyService.getDashboard(tripId), dailies));
	}

	@GetMapping("/{tripId}/dailies/{dailyId}")
	public ResponseEntity<List<PaymentDto>> readDaily(@PathVariable Long tripId, @PathVariable Long dailyId) {
		List<PaymentDto> payments = dailyService.getPayments(dailyId);
		return ResponseEntity.ok(payments);
	}

	@PostMapping("{tripId}/dailies/{dailyId}/images")
	public ResponseEntity<DailyResponse> saveImage(@PathVariable Long tripId, @PathVariable Long dailyId,
		@RequestPart(value = "imageFile", required = false) MultipartFile multipartFile) throws IOException {
		String url = s3Service.uploadImage(multipartFile);
		//저장로직 추가
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{tripId}/dailies/{dailyId}/images")
	public ResponseEntity<DailyResponse> deleteImage(@PathVariable Long tripId, @PathVariable Long dailyId) {
		s3Service.deleteImage(s3Service.getImageUrl(dailyId));
		return ResponseEntity.ok().build();
	}
}
