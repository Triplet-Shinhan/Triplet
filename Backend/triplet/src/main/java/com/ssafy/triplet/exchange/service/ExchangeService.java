package com.ssafy.triplet.exchange.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.exchange.dto.ExchangeApplyRequestDto;
import com.ssafy.triplet.exchange.dto.ExchangeApplyResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeCodeData;
import com.ssafy.triplet.exchange.dto.ExchangeData;
import com.ssafy.triplet.exchange.dto.ExchangeRateDataBody;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDataBody;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResultsResponseDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;
import com.ssafy.triplet.parser.WebClientUtil;
import com.ssafy.triplet.parser.dto.rateParser.CurrencyRate;

@Service
public class ExchangeService {
    private final WebClientUtil webClientUtil;

    public ExchangeService(WebClientUtil webClientUtil) {
        this.webClientUtil = webClientUtil;
    }

    public ExchangeResponseDto getRate(String currency) {

        // 신한 api 요청 해서 통화코드, 환율 , 환전신청단위 , 우대율

        ExchangeResponseDto exchangeResponseDto = new ExchangeResponseDto();
        // 신한 api로 부터 데이터를 가지고 온다.

        // todo : 현재 표시 가능한 모든 통화코드를 가지고 온다.
        List<ExchangeCodeData> currencyCodes = webClientUtil.get_____(); // 최소 단위 가져오기 위함

        // 환율 가지고 오기
        // TODO : 고시 환율 API 활용하여 가져오기
        List<ExchangeRateDataBody> exchangeRateDatas = webClientUtil.get____(); // 미완

        // api 성공했으면 200
        exchangeResponseDto.setResultCode(200);

        // 우대율 가지고 오기
        List<CurrencyRate> preferentialList = webClientUtil.getPreferentialRate();

        // 응답 데이터 바디
        ExchangeResponseDataBody dataBody = new ExchangeResponseDataBody();

        dataBody.setListNum(currencyCodes.size()); // 리스트 수 입력

        List<String> curCodes = new ArrayList<String>();

        // 각 통화에 대한 통화코드, 환율, 최소 단위, 우대율 파싱하기
        for (ExchangeRateDataBody curData : exchangeRateDatas) { // 각 통화별로 조사 시작
            ExchangeData ed = new ExchangeData();

            // 통화코드 설정
            ed.setCurrencyCode(curData.getCurrency());
            curCodes.add(curData.getCurrency()); // 통화 리스트에 저장

            // 환율 설정
            ed.setExchangeRate(curData.getSellingRate());

            // 최소 단위 파싱하기
            for (ExchangeCodeData cd : currencyCodes) {
                if (cd.getCurrencyCode() == curData.getCurrency()) { // 같은 이름 코드라면
                    ed.setExchangeUnit(cd.getUnit()); // 최소 단위 저장
                    break;
                }
            }

            // 우대율 파싱하기
            Preferential: for (CurrencyRate preferential : preferentialList) { // 각 우대율에서
                for (String cd : preferential.getCurrencyCode().split(",")) { // 각 통화 코드에 대해 조사 시작
                    if (cd == curData.getCurrency()) { // 같은 통화 코드라면
                        ed.setPreferentialRate(preferential.getPreferentialRate()); // 우대율 저장
                        break Preferential;
                    }
                }
            }

        }

        dataBody.setCurrencyList(curCodes); // 통화리스트 저장
        exchangeResponseDto.setDataBody(dataBody); // 데이터 바디 저장

        return exchangeResponseDto;
    }

    public NearBranchResponseDto getNearBranch(Double latitude, Double longtitude) {
        return new NearBranchResponseDto();
    }

    public ExchangeResultsResponseDto getExchangeResults() {
        return new ExchangeResultsResponseDto();
    }

    public ExchangeApplyResponseDto applyExchange(ExchangeApplyRequestDto exchangeApplyRequestDto) {
        return new ExchangeApplyResponseDto();
    }
}
