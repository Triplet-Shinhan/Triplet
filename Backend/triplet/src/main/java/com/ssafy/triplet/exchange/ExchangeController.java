package com.ssafy.triplet.exchange;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.exchange.dto.ExchangeDto;
import com.ssafy.triplet.exchange.dto.NearBranchDto;
import com.ssafy.triplet.exchange.dto.NearBranchRequestDto;
import com.ssafy.triplet.exchange.service.ExchangeService;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {

    ExchangeService exchangeService;

    @GetMapping("")
    public ExchangeDto getExchange(@RequestParam("currency") String currency) {
        return exchangeService.getRate(currency);

    }

    @GetMapping("/locations")
    public NearBranchDto getBranchLocation(NearBranchRequestDto nearBranchRequest) {
        return exchangeService.getBranch(nearBranchRequest.getLatitude(), nearBranchRequest.getLongtitude());
    }
}
