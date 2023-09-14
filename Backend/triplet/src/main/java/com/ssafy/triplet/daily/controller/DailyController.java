package com.ssafy.triplet.daily.controller;

import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.response.DailiesResponse;
import com.ssafy.triplet.daily.response.DailyResponse;
import com.ssafy.triplet.daily.service.DailyService;
import com.ssafy.triplet.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class DailyController {
    private final DailyService dailyService;

    @GetMapping("/{tripId}/dailies")
    public ResponseEntity<DailiesResponse> readDailies(@PathVariable Long tripId) {
        List<DailyDto> dailies = dailyService.toDailyDtoList(tripId);
        return ResponseEntity.ok(new DailiesResponse(tripId, dailyService.getDashboard(tripId), dailies));
    }

    @GetMapping("/{tripId}/dailies/{dailyId}")
    public ResponseEntity<List<Payment>> readDaily(@PathVariable Long tripId, @PathVariable Long dailyId) {
        List<Payment> payments = dailyService.getPayments(dailyId);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("{tripId}/dailies/{dailyId}/images")
    public ResponseEntity<DailyResponse> saveImage(@PathVariable Long tripId, @PathVariable Long dailyId) { //S3

        return null;
    }
}
