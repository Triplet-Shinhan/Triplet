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
import com.ssafy.triplet.exchange.dto.ExchangeData;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDataBody;
import com.ssafy.triplet.exchange.dto.ExchangeResponseDto;
import com.ssafy.triplet.exchange.dto.NearBranchRequestDto;
import com.ssafy.triplet.exchange.dto.NearBranchResponseDto;
import com.ssafy.triplet.exchange.util.ExchangeUtil;
import com.ssafy.triplet.parser.WebClientUtil;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeDataBody;
import com.ssafy.triplet.parser.dto.checkExchange.CheckExchangeReqDataBody;
import com.ssafy.triplet.parser.dto.currency.Currency;
import com.ssafy.triplet.parser.dto.exchange.ExchangeDataBody;
import com.ssafy.triplet.parser.dto.exchange.ExchangeReqDataBody;
import com.ssafy.triplet.parser.dto.exchangeBranch.Branch;
import com.ssafy.triplet.parser.dto.exchangeRate.ExchangeRate;
import com.ssafy.triplet.parser.dto.rateParser.CurrencyRate;
import com.ssafy.triplet.parser.dto.transfer.TransferReqDataBody;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final WebClientUtil webClientUtil;
    private final ExchangeUtil exchangeUtil;
    private final UserUtility userUtility;

    // 환전 메인 페이지를 위한 정보 불러오기 메소드
    public ExchangeResponseDto getRate(String currency) {

        // 신한 api 요청 해서 통화코드, 환율 , 환전신청단위 , 우대율

        ExchangeResponseDto exchangeResponseDto = new ExchangeResponseDto();
        // 신한 api로 부터 데이터를 가지고 온다.

        // 현재 표시 가능한 모든 통화코드를 가지고 온다.
        List<Currency> currencyCodes = webClientUtil.getAllCurrency(); // 최소 단위 가져오기 위함

        // 환율 가지고 오기
        // 고시 환율 API 활용하여 가져오기
        LocalDate now = LocalDate.now();
        List<ExchangeRate> exchangeRateDatas = webClientUtil
                .getExchangeRate(now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))); // 미완

        // api 성공했으면 200
        exchangeResponseDto.setResultCode(200);

        // 우대율 가지고 오기
        List<CurrencyRate> preferentialList = webClientUtil.getPreferentialRate();

        // 응답 데이터 바디
        ExchangeResponseDataBody dataBody = new ExchangeResponseDataBody();

        List<String> curCodes = new ArrayList<String>();
        List<ExchangeData> exchangeList = new ArrayList<ExchangeData>();

        // 각 통화에 대한 통화코드, 환율, 최소 단위, 우대율 파싱하기
        for (ExchangeRate curData : exchangeRateDatas) { // 각 통화별로 조사 시작
            ExchangeData ed = new ExchangeData();

            // 통화코드 설정
            ed.setCurrencyCode(curData.getCurrencyCode().trim());
            curCodes.add(curData.getCurrencyCode().trim()); // 통화 리스트에 저장

            // 환율 설정
            ed.setExchangeRate(curData.getExchangeRate());

            // 최소 단위 파싱하기
            for (Currency cd : currencyCodes) {
                if (cd.getCurrencyCode().trim().equals(curData.getCurrencyCode().trim())) { // 같은 이름 코드라면
                    ed.setExchangeUnit(cd.getExchangeUnit()); // 최소 단위 저장
                    break;
                }
            }

            // 우대율 파싱하기
            Preferential: for (CurrencyRate preferential : preferentialList) { // 각 우대율에서
                for (String cd : preferential.getCurrencyCode().split(",")) { // 각 통화 코드에 대해 조사 시작
                    if (cd.trim().equals(curData.getCurrencyCode().trim())) { // 같은 통화 코드라면
                        ed.setPreferentialRate(preferential.getPreferentialRate()); // 우대율 저장
                        break Preferential;
                    }
                }
            }

            exchangeList.add(ed);
        }

        // NULL 값 제외하기
        for (int idx = 0; idx < curCodes.size(); idx++) {
            if (exchangeList.get(idx).getExchangeUnit() == null
                    || exchangeList.get(idx).getPreferentialRate() == null) {
                exchangeList.remove(idx);
                curCodes.remove(idx);
                idx--;
            }
        }

        dataBody.setListNum(exchangeList.size()); // 리스트 수 입력
        dataBody.setCurrencyList(curCodes); // 통화리스트 저장
        dataBody.setExchangeData(exchangeList); // 환율 데이터 저장
        exchangeResponseDto.setDataBody(dataBody); // 데이터 바디 저장

        return exchangeResponseDto;
    }

    // 현재 사용자의 위치 기반 가까운 순서로 목록을 반환하는 메소드
    public NearBranchResponseDto getNearBranch(NearBranchRequestDto nbrReq) {

        // 응답 객체
        NearBranchResponseDto nbrRes = new NearBranchResponseDto();

        // 신한 API : 환전할 화폐에 대한 환전 가능한 지점 목록 불러오기
        List<Branch> branches = webClientUtil.getBranchName(nbrReq.getCurrency());

        // 불러오기 성공했으면
        nbrRes.setResultCode(200);
        nbrRes.setListNum(branches.size());

        List<BranchInfo> biList = new ArrayList<BranchInfo>();

        // 지점 정보 별로
        for (Branch branch : branches) {

            BranchInfo bi = new BranchInfo();

            Double distance = exchangeUtil.getDistance(nbrReq.getLatitude(), nbrReq.getLongitude(),
                    Double.parseDouble(branch.getLatitude()), Double.parseDouble(branch.getLongitude()));
            bi.setDistance(distance);
            bi.setAddress(branch.getAddress());
            bi.setAreaCode(branch.getAreaCode());
            bi.setBranchName(branch.getBranchName());
            bi.setLatitude(branch.getLatitude());
            bi.setLongitude(branch.getLongitude());
            bi.setTelNumber(branch.getTelNumber());

            biList.add(bi);
        }

        // 가까운 지점 순서로 sort
        Collections.sort(biList);

        // data body 저장
        nbrRes.setDataList(biList);
        return nbrRes;
    }

    // 사용자가 환전하였던 기록 목록을 반환한다.
    public CheckExchangeDataBody getExchangeResults(HttpServletRequest httpServletRequest) {
        CheckExchangeDataBody erRes = new CheckExchangeDataBody();

        // DB 접속 및 사용자 정보 가지고 오기
        User user = userUtility.getUserFromCookie(httpServletRequest);

        // 가져온 사용자 정보를 가지고 환전 기록을 가지고 온다.
        CheckExchangeReqDataBody erReq = new CheckExchangeReqDataBody(user.getName(), user.getPhoneNum(),
                user.getBirth()); // 신한 api 요청 dto
        erReq.setServiceCode("T0512");
        erRes = webClientUtil.getExchangeResult(erReq);

        // 가지고 온 환전 기록을 반환 한다.
        return erRes;
    }

    // 환전 신청 요청하는 메소드
    public ExchangeApplyResponseDto applyExchange(@RequestBody ExchangeApplyRequestDto exchangeApplyRequestDto,
            HttpServletRequest httpServletRequest) {

        ExchangeApplyResponseDto eaRes = new ExchangeApplyResponseDto(); // 최종 결과 DTO

        // DB 접속 및 사용자 정보 가지고 오기
        User user = userUtility.getUserFromCookie(httpServletRequest);

        ExchangeReqDataBody exchange = new ExchangeReqDataBody(); // 신한 API

        exchange.setServiceCode("T0511");
        exchange.setCurrency(exchangeApplyRequestDto.getCurrency());
        exchange.setName(user.getName()); // 신청자 이름

        exchange.setExchangeAmount(exchangeApplyRequestDto.getAmount());
        exchange.setLocation(exchangeApplyRequestDto.getLocation());
        exchange.setReceiveDate(exchangeApplyRequestDto.getReceiptDate());
        exchange.setReceiverName(user.getName()); // 수령자 이름
        exchange.setBirth(user.getBirth()); // 생일
        exchange.setPhoneNum(user.getPhoneNum()); // 전화번호
        exchange.setReceiveWay(String.valueOf(exchangeApplyRequestDto.getReceiveWay()));

        // 신한 환전 API 호출
        ExchangeDataBody edb = webClientUtil.createExchange(exchange);

        /** 자동 이체 */
        String accountNum = user.getAccountNum();

        // 이체 할 때 보낼 데이터 바디
        TransferReqDataBody tReqBody = new TransferReqDataBody();

        tReqBody.setWithdrawalAccountNum(accountNum);
        tReqBody.setDepositBankCode("088");
        tReqBody.setDepositAccountNum(edb.getVirtualAccountNumber()); // 가상 계좌로 입금
        tReqBody.setTransferAmount(edb.getConvertedKRWAmount()); // 변환된 원화 만큼 이체해야한다.
        tReqBody.setDepositAccountMemo(user.getName() + "_환전");
        tReqBody.setWithdrawalAccountMemo(exchangeApplyRequestDto.getCurrency() + "_환전 신청");

        webClientUtil.createTransfer(tReqBody); // 계좌 이체 API 호출

        // 성공
        eaRes.setResultCode("200");
        eaRes.setConvertedKRWAmount(edb.getConvertedKRWAmount());
        eaRes.setExchangeRate(edb.getExchangeRate());
        eaRes.setPreferentialRate(edb.getPreferentialRate());
        return eaRes;
    }
}
