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
import com.ssafy.triplet.exchange.dto.NearBranchRequestDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;
import com.ssafy.triplet.exchange.service.ExchangeService;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeDataBody;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;
    private final UserUtility userUtility;

    @GetMapping("")
    public ExchangeResponseDto getExchange() {
        return exchangeService.getRate();
    }

    @GetMapping("/locations")
    public NearBranchResponseDto getBranchLocation(@RequestParam(name = "latitude") String latitude,
            @RequestParam(name = "longitude") String longitude, @RequestParam(name = "currency") String currency) {
        NearBranchRequestDto nearBranchRequest = new NearBranchRequestDto(Double.parseDouble(latitude),
                Double.parseDouble(longitude), currency);
        return exchangeService.getNearBranch(nearBranchRequest);
    }

    @PostMapping("")
    public ExchangeApplyResponseDto applyExchange(HttpServletRequest httpServletRequest,
            @RequestBody ExchangeApplyRequestDto ExchangeApplyRequestDto) {

        // 사용자 정보 가지고 오기
        User user = userUtility.getUserFromCookie(httpServletRequest);
        return exchangeService.applyExchange(ExchangeApplyRequestDto, user);
    }

    @GetMapping("/results")
    public CheckExchangeDataBody getExchangeResults(HttpServletRequest httpServletRequest) {
        // 사용자 정보 가지고 오기
        User user = userUtility.getUserFromCookie(httpServletRequest);
        return exchangeService.getExchangeResults(user);
    }
}
