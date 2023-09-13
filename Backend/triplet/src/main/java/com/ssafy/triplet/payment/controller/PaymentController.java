package com.ssafy.triplet.payment.controller;

import com.ssafy.triplet.payment.dto.PaymentReqDto;
import com.ssafy.triplet.payment.service.PaymentService;
import com.ssafy.triplet.user.domain.User;
import com.ssafy.triplet.user.util.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserUtility userUtility;
    @PostMapping
    public ResponseEntity createPayment(@RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request){
        User user = userUtility.getUserFromCookie(request);

        paymentService.createPayment(paymentReqDto,user);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity updatePayment(@PathVariable Long paymentId,@RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request){
        userUtility.getUserFromCookie(request);

        paymentService.updatePayment(paymentReqDto,paymentId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity deletePayment(@PathVariable Long paymentId,@RequestBody PaymentReqDto paymentReqDto,HttpServletRequest request){
        User user = userUtility.getUserFromCookie(request);

        paymentService.deletePayment(paymentReqDto,user,paymentId);
        return ResponseEntity.ok().build();
    }
}
