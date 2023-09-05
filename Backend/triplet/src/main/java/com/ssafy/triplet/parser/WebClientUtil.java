package com.ssafy.triplet.parser;

import com.ssafy.triplet.parser.dto.DataBodyRequest;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.ExchangeBranch.Branch;
import com.ssafy.triplet.parser.dto.ExchangeBranch.BranchReqDataBody;
import com.ssafy.triplet.parser.dto.ExchangeBranch.BranchReqDto;
import com.ssafy.triplet.parser.dto.ExchangeBranch.BranchResDto;
import com.ssafy.triplet.parser.dto.exchange.ExchangeReqDataBody;
import com.ssafy.triplet.parser.dto.exchange.ExchangeDataBody;
import com.ssafy.triplet.parser.dto.exchange.ExchangeReqDto;
import com.ssafy.triplet.parser.dto.exchange.ExchangeResDto;
import com.ssafy.triplet.parser.dto.rateParser.CurrencyRate;
import com.ssafy.triplet.parser.dto.rateParser.RateReqDto;
import com.ssafy.triplet.parser.dto.rateParser.RateResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WebClientUtil {
    @Value("${parser.api-key}")
    private String apiKey;

    private final WebClient webClient;

    public WebClientUtil(WebClient webClient) {
        this.webClient = webClient;
    }


    //우대율 api 반환하는 메소드
    public List<CurrencyRate> getPreferentialRate() {
        String url = "https://shbhack.shinhan.com/v1/search/fx/discount-rate";

        //헤더 설정
        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);
        //요청body 설정
        DataBodyRequest dataBody = new DataBodyRequest();
        dataBody.setServiceCode("T0501");

        // API 요청 객체 설정
        RateReqDto rateReqDto = new RateReqDto();
        rateReqDto.setDataHeader(dataHeader);
        rateReqDto.setDataBody(dataBody);

        RateResDto rateResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(rateReqDto)
                .retrieve()
                .bodyToMono(RateResDto.class)
                .block();

        //에러처리


        return rateResDto.getRateDataBody().getCurrencyRate();
    }



    //환전신청 api
    public ExchangeDataBody createExchange(ExchangeReqDataBody exchangeReqDataBody){
        String url = "https://shbhack.shinhan.com/v1/request/fx";
        //헤더 설정
        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);
        //요청body 설정
        exchangeReqDataBody.setServiceCode("T0511");

        // API 요청 객체 설정
        ExchangeReqDto exchangeReqDto = new ExchangeReqDto();
        exchangeReqDto.setDataHeader(dataHeader);
        exchangeReqDto.setDataBody(exchangeReqDataBody);

        ExchangeResDto exchangeResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(exchangeReqDto)
                .retrieve()
                .bodyToMono(ExchangeResDto.class)
                .block();

        //에러처리

        return exchangeResDto.getExchangeDataBody();
    }

    public List<Branch> getBranchName(String currency){
        String url = "https://shbhack.shinhan.com/v1/search/branch/city";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);

        BranchReqDataBody branchReqDataBody= new BranchReqDataBody();
        branchReqDataBody.setCurrency(currency);
        branchReqDataBody.setServiceCode("T0506");

        BranchReqDto branchReqDto = new BranchReqDto();
        branchReqDto.setDataHeader(dataHeader);
        branchReqDto.setDataBody(branchReqDataBody);

        BranchResDto branchResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(branchReqDto)
                .retrieve()
                .bodyToMono(BranchResDto.class)
                .block();

        //에러처리

        return branchResDto.getDataBody().getBranchList();
    }
}
