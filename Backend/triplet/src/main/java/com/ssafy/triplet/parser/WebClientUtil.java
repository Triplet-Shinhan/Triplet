package com.ssafy.triplet.parser;

import com.ssafy.triplet.parser.dto.DataBodyRequest;
import com.ssafy.triplet.parser.dto.DataHeaderRequest;
import com.ssafy.triplet.parser.dto.acount.AccountDataBody;
import com.ssafy.triplet.parser.dto.acount.AccountReqDataBody;
import com.ssafy.triplet.parser.dto.acount.AccountReqDto;
import com.ssafy.triplet.parser.dto.acount.AccountResDto;
import com.ssafy.triplet.parser.dto.checkAccount.AuthRequestDto;
import com.ssafy.triplet.parser.dto.checkAccount.CheckAccount;
import com.ssafy.triplet.parser.dto.checkAccount.CheckAccountReqDto;
import com.ssafy.triplet.parser.dto.checkAccount.CheckAccountResDto;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeDataBody;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeReqDataBody;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeReqDto;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeResDto;
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
import com.ssafy.triplet.parser.dto.solPush.SolPushReqDataBody;
import com.ssafy.triplet.parser.dto.solPush.SolPushReqDto;
import com.ssafy.triplet.parser.dto.transfer.TransferDataBody;
import com.ssafy.triplet.parser.dto.transfer.TransferReqDataBody;
import com.ssafy.triplet.parser.dto.transfer.TransferReqDto;
import com.ssafy.triplet.parser.dto.transfer.TransferResDto;
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

    //지점명
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

    //환율
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


        return exchangeRateResDto.getDataBody().getExchangeRateList();
    }

    //통화
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

    //환전 결과 조회
    public CheckExchangeDataBody getExchangeResult(CheckExchangeReqDataBody checkExchangeReqDataBody){
        String url = "https://shbhack.shinhan.com/v1/search/fx/request-list";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        checkExchangeReqDataBody.setServiceCode("T0512");
        CheckExchangeReqDto checkExchangeReqDto = new CheckExchangeReqDto();
        checkExchangeReqDto.setDataHeader(dataHeader);
        checkExchangeReqDto.setDataBody(checkExchangeReqDataBody);

        CheckExchangeResDto checkAccountResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(checkExchangeReqDto )
                .retrieve()
                .bodyToMono(CheckExchangeResDto.class)
                .block();

        return checkAccountResDto.getCheckExchangeDataBody();

    }

    //이체
    public TransferDataBody createTransfer(TransferReqDataBody transferReqDataBody){
        String url = "https://shbhack.shinhan.com/v1/transfer/krw";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        TransferReqDto transferReqDto = new TransferReqDto();
        transferReqDto.setDataHeader(dataHeader);
        transferReqDto.setDataBody(transferReqDataBody);

        TransferResDto transferResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(transferReqDto )
                .retrieve()
                .bodyToMono(TransferResDto.class)
                .block();

        return transferResDto.getTransferDataBody();
    }

    //거래내역 조회
    public AccountDataBody getAccount(String accountNum){
        String url = "https://shbhack.shinhan.com/v1/search/transaction";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        AccountReqDto accountReqDto = new AccountReqDto();
        accountReqDto.setDataHeader(dataHeader);
        AccountReqDataBody accountReqDataBody=new AccountReqDataBody(accountNum);
        accountReqDto.setDataBody(accountReqDataBody);

        AccountResDto accountResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(accountReqDto )
                .retrieve()
                .bodyToMono(AccountResDto.class)
                .block();

        return accountResDto.getAccountDataBody();
    }

    //솔푸쉬 알람
    public boolean solPush(String customerNum,String sendMessage){
        String url = "https://shbhack.shinhan.com/v1/notice/sol-push";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        SolPushReqDto solPushReqDto = new SolPushReqDto();
        solPushReqDto.setDataHeader(dataHeader);
        SolPushReqDataBody solPushReqDataBody= new SolPushReqDataBody(customerNum,sendMessage);
        solPushReqDto.setDataBody(solPushReqDataBody);

        AccountResDto accountResDto=webClient.post()
                .uri(url)
                .header("Content-Type", "application/json; charset=UTF-8")
                .bodyValue(solPushReqDto )
                .retrieve()
                .bodyToMono(AccountResDto.class)
                .block();

        return true;
    }
}
