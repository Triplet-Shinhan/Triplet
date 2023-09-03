package com.ssafy.triplet.payment.controller;

import com.ssafy.triplet.parser.Object.ExchangeResult;
import com.ssafy.triplet.payment.dto.PaymentReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping
    public ResponseEntity createPayment(@RequestBody PaymentReqDto paymentReqDto){
        return ResponseEntity.ok(200);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity updatePayment(@PathVariable long paymentId,@RequestBody PaymentReqDto paymentReqDto){
        return ResponseEntity.ok(200);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity deletePayment(@PathVariable long paymentId,@RequestBody PaymentReqDto paymentReqDto){
        return ResponseEntity.ok(200);
    }
}
