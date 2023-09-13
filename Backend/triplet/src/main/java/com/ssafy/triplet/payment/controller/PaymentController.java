package com.ssafy.triplet.payment.controller;

import com.ssafy.triplet.payment.dto.PaymentReqDto;
import com.ssafy.triplet.payment.service.PaymentService;
import com.ssafy.triplet.user.domain.User;
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

    @PostMapping
    public ResponseEntity createPayment(@RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        paymentService.createPayment(paymentReqDto,user);

        return ResponseEntity.ok(200);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity updatePayment(@PathVariable Long paymentId,@RequestBody PaymentReqDto paymentReqDto, HttpServletRequest request){

        return ResponseEntity.ok(200);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity deletePayment(@PathVariable Long paymentId,@RequestBody PaymentReqDto paymentReqDto){
        return ResponseEntity.ok(200);
    }
}
