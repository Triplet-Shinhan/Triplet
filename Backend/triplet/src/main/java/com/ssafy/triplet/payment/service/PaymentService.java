package com.ssafy.triplet.payment.service;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.payment.dto.PaymentReqDto;
import com.ssafy.triplet.payment.repository.PaymentRepository;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.triplet.exception.ErrorCode.*;

@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final TripRepository tripRepository;
    private final PaymentRepository paymentRepository;
    private final DailyRepository dailyRepository;
    //private final DailyRepository;
    public String createPayment(PaymentReqDto reqDto, User user){
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

        //
        paymentRepository.save(payment);

        return "success";
    }

    public String updatePayment(PaymentReqDto reqDto, Long paymentId) {
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

        return "success";
    }
}
