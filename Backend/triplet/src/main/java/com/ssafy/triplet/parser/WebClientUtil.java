package com.ssafy.triplet.parser;

import com.ssafy.triplet.parser.dto.DataBodyRequest;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.rateParser.RateReqDto;
import com.ssafy.triplet.parser.dto.rateParser.RateResDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientUtil {

    private final WebClient webClient;

    public WebClientUtil(WebClient webClient) {
        this.webClient = webClient;
    }


    //우대율 api 반환하는 메소드
    public RateResDto getRate() {
        String url = "https://shbhack.shinhan.com/v1/search/fx/discount-rate";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey("2023_Shinhan_SSAFY_Hackathon");

        DataBodyRequest dataBody = new DataBodyRequest();
        dataBody.setServiceCode("T0501");

        // API 요청 객체 설정
        RateReqDto rateReqDto = new RateReqDto();
        rateReqDto.setDataHeader(dataHeader);
        rateReqDto.setDataBody(dataBody);

        return webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(rateReqDto)
                .retrieve()
                .bodyToMono(RateResDto.class)
                .block();
    }
}
