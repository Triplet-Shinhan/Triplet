package com.ssafy.triplet.daily.controller;

import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.dto.PaymentDto;
import com.ssafy.triplet.daily.response.DailiesResponse;
import com.ssafy.triplet.daily.response.DailyResponse;
import com.ssafy.triplet.daily.response.ImageUrlResponse;
import com.ssafy.triplet.daily.service.DailyService;
import com.ssafy.triplet.daily.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @PostMapping("/{tripId}/dailies/{dailyId}/images")
    public ResponseEntity<ImageUrlResponse> saveImage(@PathVariable Long tripId, @PathVariable Long dailyId,
                                                      @RequestPart(value = "image", required = false) MultipartFile multipartFile) throws IOException {
        String url = s3Service.uploadImage(multipartFile);
        dailyService.saveImageUrl(dailyId, url);//이미지 저장
        return ResponseEntity.ok(new ImageUrlResponse(url));
    }

    @DeleteMapping("/{tripId}/dailies/{dailyId}/images")
    public ResponseEntity<DailyResponse> deleteImage(@PathVariable Long tripId, @PathVariable Long dailyId) {
        s3Service.deleteImage(s3Service.getImageUrl(dailyId));
        return ResponseEntity.ok().build();
    }
}
