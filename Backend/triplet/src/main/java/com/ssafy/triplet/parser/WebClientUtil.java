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

import java.util.ArrayList;
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

//        RateResDto rateResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(rateReqDto)
//                .retrieve()
//                .bodyToMono(RateResDto.class)
//                .block();

        //에러처리

        //신한 api 대체
        List<CurrencyRate> lists = new ArrayList<CurrencyRate>();
        CurrencyRate ninety = new CurrencyRate();
        ninety.setOrder("1");
        ninety.setPreferentialRate("90");
        ninety.setCurrencyCode("USD, JPY, EUR");

        CurrencyRate fifty = new CurrencyRate();
        fifty.setOrder("2");
        fifty.setPreferentialRate("50");
        ninety.setCurrencyCode("CAD, HKD, AUD, CNY, THB, GBP, CHF, SGD, NZD");

        CurrencyRate thirty = new CurrencyRate();
        thirty.setOrder("2");
        thirty.setPreferentialRate("30");
        thirty.setCurrencyCode("PHP, TWD, IDR, AED, VND, MYR");

        lists.add(ninety);
        lists.add(fifty);
        lists.add(thirty);
        //신한 api 대체 end

        //return rateResDto.getRateDataBody().getCurrencyRate();
        return lists;
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

//        ExchangeResDto exchangeResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(exchangeReqDto)
//                .retrieve()
//                .bodyToMono(ExchangeResDto.class)
//                .block();
        //신한 api 대체
        ExchangeDataBody result = new ExchangeDataBody();
        result.setExchangeRate("1327.31");
        result.setPreferentialRate("90");
        result.setConvertedKRWAmount("132731");
        result.setVirtualAccountNumber("9999999999999");
        result.setVirtualAccountDepositAmount("132731");
        result.setVirtualDepositDeadlineDate("20230421");
        result.setVirtualDepositDeadlineTime("162706");
        //신한 api 대체 end

        //에러처리

//        return exchangeResDto.getExchangeDataBody();
        return result;
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

//        BranchResDto branchResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(branchReqDto)
//                .retrieve()
//                .bodyToMono(BranchResDto.class)
//                .block();
        List<Branch> branches = new ArrayList<Branch>();

        Branch first = new Branch();
        first.setBranchName("인천국제공항 제 1 여객터미널");
        first.setAddress("인천 중구 공항로 272(운서동), 교통센터 지하 1층");
        first.setLongitude("126.452267145872");
        first.setLatitude("37.447778433456");
        first.setTelNumber("032-452-0772");
        first.setAreaCode("0001A");

        Branch second = new Branch();
        second.setBranchName("인천국제공항제2여객터미널(출)");
        second.setAddress("인천 중구 공항로 272(운서동), 교통센터 지하 1층");
        second.setLongitude("126.452267145872");
        second.setLatitude("37.447778433456");
        second.setTelNumber("032-743-5100");
        second.setAreaCode("0001B");

        branches.add(first);
        branches.add(second);
        //에러처리

//        return branchResDto.getDataBody().getBranchList();
        return branches;
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

//        ExchangeRateResDto exchangeRateResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(exchangeRateReqDto)
//                .retrieve()
//                .bodyToMono(ExchangeRateResDto.class)
//                .block();
        List<ExchangeRate> rates = new ArrayList<>();

        ExchangeRate er = new ExchangeRate();
        er.setExchangeRate("1310.03");
        er.setCurrencyCode("USD");
        er.setCurrencyDisplay("미국 달러");
        er.setZeroRate("1287.50");

        ExchangeRate japan = new ExchangeRate();
        japan.setExchangeRate("916.36");
        japan.setCurrencyCode("JPY");
        japan.setCurrencyDisplay("일본 100엔");
        japan.setZeroRate("900.12");

        rates.add(er);
        rates.add(japan);
//        return exchangeRateResDto.getDataBody().getExchangeRateList();
        return rates;
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

//        CurrencyResDto currencyResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(currencyReqDto)
//                .retrieve()
//                .bodyToMono(CurrencyResDto.class)
//                .block();

        List<Currency> currencies = new ArrayList<>();

        Currency usd = new Currency();
        usd.setCurrencyCode("USD");
        usd.setCurrencyName("미국달러(USD)");
        usd.setExchangeUnit("10");

        Currency jpy = new Currency();
        usd.setCurrencyCode("JPY");
        usd.setCurrencyName("일본엔(JPY)");
        usd.setExchangeUnit("1000");

        Currency eur = new Currency();
        usd.setCurrencyCode("EUR");
        usd.setCurrencyName("유럽유로(EUR)");
        usd.setExchangeUnit("10");

        currencies.add(usd);
        currencies.add(jpy);
        currencies.add(eur);

        return currencies;
     //   return currencyResDto.getCurrencyDataBody().getCurrencyList();
    }

    //1원 이체
    public CheckAccount checkAccount(AuthRequestDto authRequestDto){
        String url = "https://shbhack.shinhan.com/v1/auth/1transfer";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        CheckAccountReqDto checkAccountReqDto = new CheckAccountReqDto();
        checkAccountReqDto.setDataHeader(dataHeader);
        checkAccountReqDto.setDataBody(authRequestDto);

//        CheckAccountResDto checkAccountResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(checkAccountReqDto )
//                .retrieve()
//                .bodyToMono(CheckAccountResDto.class)
//                .block();
        CheckAccount ca = new CheckAccount();
        ca.setAccountNum("110222999999");
        ca.setBankCode("088");
        return ca;
        //return checkAccountResDto.getCheckAccount();
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

//        CheckExchangeResDto checkAccountResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(checkExchangeReqDto )
//                .retrieve()
//                .bodyToMono(CheckExchangeResDto.class)
//                .block();
        CheckExchangeDataBody result = new CheckExchangeDataBody();
        return null;
        //return checkAccountResDto.getCheckExchangeDataBody();

    }

    //이체
    public TransferDataBody createTransfer(TransferReqDataBody transferReqDataBody){
        String url = "https://shbhack.shinhan.com/v1/transfer/krw";

        DataHeaderRequest dataHeader = new DataHeaderRequest();
        dataHeader.setApikey(apiKey);


        TransferReqDto transferReqDto = new TransferReqDto();
        transferReqDto.setDataHeader(dataHeader);
        transferReqDto.setDataBody(transferReqDataBody);

//        TransferResDto transferResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(transferReqDto )
//                .retrieve()
//                .bodyToMono(TransferResDto.class)
//                .block();
        TransferDataBody result =new TransferDataBody();
        result.setBalance("1500");
        return result;
        //return transferResDto.getTransferDataBody();
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

//        AccountResDto accountResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(accountReqDto )
//                .retrieve()
//                .bodyToMono(AccountResDto.class)
//                .block();
        return null;
        //return accountResDto.getAccountDataBody();
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

//        AccountResDto accountResDto=webClient.post()
//                .uri(url)
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .bodyValue(solPushReqDto )
//                .retrieve()
//                .bodyToMono(AccountResDto.class)
//                .block();

        return true;
    }
}
