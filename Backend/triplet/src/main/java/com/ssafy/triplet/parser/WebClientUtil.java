package com.ssafy.triplet.parser;

import com.ssafy.triplet.parser.dto.DataBodyRequest;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.checkAccount.AuthRequestDto;
import com.ssafy.triplet.parser.dto.checkAccount.CheckAccount;
import com.ssafy.triplet.parser.dto.checkAccount.CheckAccountReqDto;
import com.ssafy.triplet.parser.dto.checkAccount.CheckAccountResDto;
import com.ssafy.triplet.parser.dto.currency.Currency;
import com.ssafy.triplet.parser.dto.currency.CurrencyReqDto;
import com.ssafy.triplet.parser.dto.currency.CurrencyResDto;
import com.ssafy.triplet.parser.dto.exchangeBranch.Branch;
import com.ssafy.triplet.parser.dto.exchangeBranch.BranchReqDataBody;
import com.ssafy.triplet.parser.dto.exchangeBranch.BranchReqDto;
import com.ssafy.triplet.parser.dto.exchangeBranch.BranchResDto;
import com.ssafy.triplet.parser.dto.exchange.ExchangeReqDataBody;
import com.ssafy.triplet.parser.dto.exchange.ExchangeDataBody;
import com.ssafy.triplet.parser.dto.exchange.ExchangeReqDto;
import com.ssafy.triplet.parser.dto.exchange.ExchangeResDto;
import com.ssafy.triplet.parser.dto.exchangeRate.ExchangeRate;
import com.ssafy.triplet.parser.dto.exchangeRate.ExchangeRateReqDataBody;
import com.ssafy.triplet.parser.dto.exchangeRate.ExchangeRateReqDto;
import com.ssafy.triplet.parser.dto.exchangeRate.ExchangeRateResDto;
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

    public List<ExchangeRate> getExchangeRate(String findDate){
        String url = "https://shbhack.shinhan.com/v1/search/fxrate/number";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);

        ExchangeRateReqDataBody exchangeRateReqDataBody= new ExchangeRateReqDataBody();
        exchangeRateReqDataBody.setFindDate(findDate);

        ExchangeRateReqDto exchangeRateReqDto = new ExchangeRateReqDto();
        exchangeRateReqDto.setDataHeader(dataHeader);
        exchangeRateReqDto.setDataBody(exchangeRateReqDataBody);

        ExchangeRateResDto exchangeRateResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(exchangeRateReqDto)
                .retrieve()
                .bodyToMono(ExchangeRateResDto.class)
                .block();

        //에러처리

        return exchangeRateResDto.getDataBody().getExchangeRateList();
    }


    public List<Currency> getAllCurrency(){
        String url = "https://shbhack.shinhan.com/v1/search/fx/currencycode";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);

        DataBodyRequest currencyReqDataBody= new DataBodyRequest();
        currencyReqDataBody.setServiceCode("T0503");

        CurrencyReqDto currencyReqDto = new CurrencyReqDto();
        currencyReqDto.setDataHeader(dataHeader);
        currencyReqDto.setDataBody(currencyReqDataBody);

        CurrencyResDto currencyResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(currencyReqDto)
                .retrieve()
                .bodyToMono(CurrencyResDto.class)
                .block();

        return currencyResDto.getCurrencyDataBody().getCurrencyList();
    }

    //1원 이체
    public CheckAccount checkAccount(AuthRequestDto authRequestDto){
        String url = "https://shbhack.shinhan.com/v1/auth/1transfer";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        CheckAccountReqDto checkAccountReqDto = new CheckAccountReqDto();
        checkAccountReqDto.setDataHeader(dataHeader);
        checkAccountReqDto.setDataBody(authRequestDto);

        CheckAccountResDto checkAccountResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(checkAccountReqDto )
                .retrieve()
                .bodyToMono(CheckAccountResDto.class)
                .block();

        return checkAccountResDto.getCheckAccount();
    }
}
