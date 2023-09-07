package com.ssafy.triplet.exchange.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.triplet.exchange.dto.BranchInfo;
import com.ssafy.triplet.exchange.dto.ExchangeApplyRequestDto;
import com.ssafy.triplet.exchange.dto.ExchangeApplyResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeCodeData;
import com.ssafy.triplet.exchange.dto.ExchangeData;
import com.ssafy.triplet.exchange.dto.ExchangeRateDataBody;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDataBody;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDto;
import com.ssafy.triplet.exchange.dto.ExchangeResultsResponseDto;
import com.ssafy.triplet.exchange.dto.NearBranchRequestDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;
import com.ssafy.triplet.exchange.util.ExchangeUtil;
import com.ssafy.triplet.parser.WebClientUtil;
import com.ssafy.triplet.parser.dto.rateParser.CurrencyRate;

@Service
public class ExchangeService {
    private final WebClientUtil webClientUtil;
    private final ExchangeUtil exchangeUtil;

    public ExchangeService(WebClientUtil webClientUtil, ExchangeUtil exchangeUtil) {
        this.webClientUtil = webClientUtil;
        this.exchangeUtil = exchangeUtil;
    }

    public ExchangeResponseDto getRate(String currency) {

        // 신한 api 요청 해서 통화코드, 환율 , 환전신청단위 , 우대율

        ExchangeResponseDto exchangeResponseDto = new ExchangeResponseDto();
        // 신한 api로 부터 데이터를 가지고 온다.

        // todo : 현재 표시 가능한 모든 통화코드를 가지고 온다.
        List<ExchangeCodeData> currencyCodes = webClientUtil.getAllCurrency(); // 최소 단위 가져오기 위함

        // 환율 가지고 오기
        // TODO : 고시 환율 API 활용하여 가져오기
        LocalDate now = LocalDate.now();
        List<ExchangeRateDataBody> exchangeRateDatas = webClientUtil
                .getExchangeRate(now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))); // 미완

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

    // 현재 사용자의 위치 기반 가까운 순서로 목록을 반환하는 메소드
    public NearBranchResponseDto getNearBranch(@RequestBody NearBranchRequestDto nbrReq) {

        // 응답 객체
        NearBranchResponseDto nbrRes = new NearBranchResponseDto();

        // 신한 API : 환전할 화폐에 대한 환전 가능한 지점 목록 불러오기
        List<Branch> branches = webClientUtil.getBranchName(nbrReq.getCurrency());

        // 불러오기 성공했으면
        nbrRes.setResultCode(200);
        nbrRes.setListNum(branches.length());

        List<BranchInfo> biList = new ArrayList<BranchInfo>();

        // TODO: 지점 정보 별로
        for (Branch branch : branches) {

            BranchInfo bi = new BranchInfo();

            Double distance = exchangeUtil.getDistance(nbrReq.getLatitude(), nbrReq.getLongitude(),
                    branch.getLatitude(), branch.getLongitude());
            bi.setDistance(distance);
            bi.setAddress(branch.getAddress());
            bi.setAreaCode(branch.getAreaCode());
            bi.setBranchName(branch.getBranchName());
            bi.setLatitude(branch.getLatitude());
            bi.setLongitude(branch.getLongitude());
            bi.setTelNumber(branch.telNumber());

            biList.add(bi);
        }

        // 가까운 지점 순서로 sort
        Collections.sort(biList);

        // data body 저장
        nbrRes.setDataList(biList);
        return nbrRes;
    }

    // 사용자가 환전하였던 기록 목록을 반환한다.
    public ExchangeResultsResponseDto getExchangeResults() {
        // TODO : DB 접속 및 사용자 정보 가지고 오기
        // ExchangeReqDataBody erdb = getDB();

        return new ExchangeResultsResponseDto();
    }

    // 환전 신청 요청하는 메소드
    public ExchangeApplyResponseDto applyExchange(@RequestBody ExchangeApplyRequestDto exchangeApplyRequestDto) {
        //
        return new ExchangeApplyResponseDto();
    }
}
