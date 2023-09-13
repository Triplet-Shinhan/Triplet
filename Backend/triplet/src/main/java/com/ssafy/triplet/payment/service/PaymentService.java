package com.ssafy.triplet.payment.service;

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
@Transactional
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final TripRepository tripRepository;
    private final PaymentRepository paymentRepository;

    //private final DailyRepository;
    public String createPayment(PaymentReqDto reqDto, User user){
        Trip trip = tripRepository.findById(reqDto.getTripId()).orElseThrow();

        //payment 객체 생성
        Payment payment = Payment.builder()
                .trip(trip)
                //.daily(daily)
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

    public String updatePayment(PaymentReqDto reqDto, User user,Long paymentId) {
        paymentRepository.findById(paymentId).orElseThrow();//존재하지 않는 paymentId 에러
        Trip trip = tripRepository.findById(reqDto.getTripId()).orElseThrow();//존재하지 않는 여행 ID 에러

        //payment 객체 생성
        Payment payment = Payment.builder()
                .trip(trip)
                //.daily(daily)
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
}
