package com.ssafy.triplet.exchange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.exchange.dto.ExchangeApplyRequestDto;
import com.ssafy.triplet.exchange.dto.ExchangeApplyResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResultsResponseDto;
import com.ssafy.triplet.exchange.dto.NearBranchRequestDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;
import com.ssafy.triplet.exchange.service.ExchangeService;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeDataBody;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("")
    public ExchangeResponseDto getExchange(@RequestParam("currency") String currency) {
        return exchangeService.getRate(currency);

    }

    @GetMapping("/locations")
    public NearBranchResponseDto getBranchLocation(@RequestBody NearBranchRequestDto nearBranchRequest) {
        return exchangeService.getNearBranch(nearBranchRequest);
    }

    @PostMapping("")
    public ExchangeApplyResponseDto applyExchange(HttpServletRequest httpServletRequest,
            @RequestBody ExchangeApplyRequestDto ExchangeApplyRequestDto) {

        return exchangeService.applyExchange(ExchangeApplyRequestDto, httpServletRequest);
    }

    @GetMapping("/results")
    public CheckExchangeDataBody getExchangeResults(HttpServletRequest httpServletRequest) {
        return exchangeService.getExchangeResults(httpServletRequest);
    }
}
