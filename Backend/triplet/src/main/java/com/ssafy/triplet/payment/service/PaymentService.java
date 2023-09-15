package com.ssafy.triplet.payment.service;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.parser.WebClientUtil;
import com.ssafy.triplet.parser.dto.acount.AccountDataBody;
import com.ssafy.triplet.parser.dto.acount.Transaction;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.payment.dto.PaymentReqDto;
import com.ssafy.triplet.payment.repository.PaymentRepository;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.ssafy.triplet.exception.ErrorCode.*;

@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final TripRepository tripRepository;
    private final PaymentRepository paymentRepository;
    private final DailyRepository dailyRepository;
    private final WebClientUtil webClientUtil;

    public void createPayment(PaymentReqDto reqDto, User user) {
        Trip trip = tripRepository.findById(reqDto.getTripId()).orElseThrow(() -> new BaseException(TRIP_ID_NOT_FOUND));
        Daily daily = dailyRepository.findById(reqDto.getDailyId()).orElseThrow(() -> new BaseException(PAYMENT_ID_NOT_FOUND));

        //payment 객체 생성
        Payment payment = Payment.builder()
                .trip(trip)
                .daily(daily)
                .item(reqDto.getItem())
                .cost(reqDto.getCost())
                .foreignCurrency(reqDto.getForeignCurrency())
                .date(reqDto.getDate())
                .method("cash")
                .build();

        paymentRepository.save(payment);
    }

    public void updatePayment(PaymentReqDto reqDto, Long paymentId) {
        paymentRepository.findById(paymentId).orElseThrow(() -> new BaseException(DAILY_ID_NOT_FOUND));
        Trip trip = tripRepository.findById(reqDto.getTripId()).orElseThrow(() -> new BaseException(TRIP_ID_NOT_FOUND));
        Daily daily = dailyRepository.findById(reqDto.getDailyId()).orElseThrow(() -> new BaseException(PAYMENT_ID_NOT_FOUND));

        //payment 객체 생성
        Payment payment = Payment.builder()
                .trip(trip)
                .daily(daily)
                .item(reqDto.getItem())
                .cost(reqDto.getCost())
                .foreignCurrency(reqDto.getForeignCurrency())
                .date(reqDto.getDate())
                .method("cash")
                .build();


        paymentRepository.save(payment);
    }

    public void deletePayment(User user, Long paymentId) {
        //id존재확인
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new BaseException(PAYMENT_ID_NOT_FOUND));

        Trip trip = tripRepository.findById(payment.getTrip().getTripId()).orElseThrow(() -> new BaseException(TRIP_ID_NOT_FOUND));
        //본인지출 확인
        if (trip.getUser().getUserId() != user.getUserId()) {
            throw new BaseException(NOT_AUTHORIZED);
        }

        paymentRepository.deleteById(payment.getPaymentId());
    }


    //신한api에서 데이터 조회
    public void updatePaymentList(User user, Long tripId) {
        AccountDataBody accountDataBody = webClientUtil.getAccount(user.getAccountNum());
        //해당 tripId에 해당하는 dailyList가져옴

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(TRIP_ID_NOT_FOUND));
        //payments의 최신값 보다 더 최신인 databody(카드결제)
        LocalDateTime startDate = trip.getStartDate().atStartOfDay();
        LocalDateTime endDate = trip.getEndDate().atTime(LocalTime.MAX);

        List<Payment> payments = trip.getPayments();
        List<Daily> dailies = trip.getDailies();
        dailies.get(0).getDailyId();//가장 첫번째

        if (payments != null) {//날짜 해당하는 지출 db에 추가

        }

        LocalDateTime lastDateTime = startDate;//카드지출중최신값

        //카드지출중 가장 최신값 찾기
        for (Payment payment : payments) {
            if (lastDateTime.isAfter(payment.getDate()) && payment.getMethod().equals("Card")) {//신한 체크 사용
                lastDateTime = payment.getDate();
            }
        }

        //가장 위에있는 최신값의 날짜 및 시간 가져오기
        List<Transaction> datas = accountDataBody.getTransactions();
        for (Transaction data : datas) {

            String lastDateStr = data.getTransactionDate();
            String lastTimeStr = data.getTransactionTime();
            LocalDateTime apiDateTime = StringToDateTime(lastDateStr + lastTimeStr);

            if (lastDateTime.isAfter(apiDateTime)) break;//

            if (lastDateTime.isBefore(apiDateTime) && data.getTransactionSummary().equals("신한체크")) {//신한체크 인지 확인 + 가장최근 카드기록보다 뒤에 값일때
                Long amount = 0L;
                if (Long.parseLong(data.getWithdrawalAmount()) > 0) {
                    amount = Long.parseLong(data.getWithdrawalAmount());//입금
                } else {
                    amount = -1 * Long.parseLong(data.getDepositAmount());//출금
                }
                Daily now = dailies.get(startDate.compareTo(apiDateTime));

                Payment pay = Payment.builder()
                        .cost(amount)
                        .date(apiDateTime)
                        .daily(now)
                        .trip(trip)
                        .method("Card")
                        .foreignCurrency("WON")
                        .item(data.getContent())
                        .build();
                System.out.println(startDate + " ~ " + endDate);
                System.out.println("저장된 지출 시간 : " + apiDateTime);
                paymentRepository.save(pay);
            }
        }


    }


    public LocalDateTime StringToDateTime(String dateString) {
        String ldtStr = dateString;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        LocalDateTime lastDateTime = LocalDateTime.parse(ldtStr, dateFormat);

        return lastDateTime;
    }
}
