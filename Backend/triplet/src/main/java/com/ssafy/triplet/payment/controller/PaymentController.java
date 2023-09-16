package com.ssafy.triplet.payment.controller;

import com.ssafy.triplet.payment.dto.PaymentReqDto;
import com.ssafy.triplet.payment.service.PaymentService;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;
import com.ssafy.triplet.user.util.UserValidation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserUtility userUtility;
    private final UserValidation userValidation;

    @PostMapping
    public ResponseEntity createPayment(@RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request) {
        User user = userUtility.getUserFromCookie(request);

        userValidation.checkDailyValid(paymentReqDto.getDailyId(), request);

        paymentService.createPayment(paymentReqDto, user);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity updatePayment(@PathVariable Long paymentId, @RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request) {

        userValidation.checkPaymentValid(paymentId, request);

        paymentService.updatePayment(paymentReqDto, paymentId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity deletePayment(@PathVariable Long paymentId, HttpServletRequest request) {
        User user = userUtility.getUserFromCookie(request);

        userValidation.checkPaymentValid(paymentId, request);

        paymentService.deletePayment(user, paymentId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cards")
    public ResponseEntity createPaymentForCard(@RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request) {
        userValidation.checkDailyValid(paymentReqDto.getDailyId(), request);

        paymentService.createPaymentForCard(paymentReqDto);

        return ResponseEntity.ok().build();
    }
}
