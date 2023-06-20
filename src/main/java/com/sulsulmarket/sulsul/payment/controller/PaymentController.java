package com.sulsulmarket.sulsul.payment.controller;

import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
import com.sulsulmarket.sulsul.config.PaymentRequest;
import com.sulsulmarket.sulsul.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {

        // 아임포트 결제 요청 처리
        String paymentResult = paymentService.processPayment(paymentRequest);

        if (paymentResult.equals("success")) {
            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed");
        }
    }
}
