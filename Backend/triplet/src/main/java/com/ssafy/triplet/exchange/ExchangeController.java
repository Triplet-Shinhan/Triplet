package com.ssafy.triplet.exchange;

import com.ssafy.triplet.exchange.dto.ExchangeDto;
import com.ssafy.triplet.exchange.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {

    ExchangeService exchangeService;
    @GetMapping("")
    public ExchangeDto getExchange(@RequestParam("currency") String currency ){

        return exchangeService.getRate(currency);

    }
}
