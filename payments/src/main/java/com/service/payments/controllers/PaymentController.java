package com.service.payments.controllers;

import com.service.payments.dtos.request.PaymentRequest;
import com.service.payments.dtos.response.PaymentResponse;
import com.service.payments.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest paymentRequest
    ) {

        PaymentResponse paymentResponse = paymentService.processPayment(paymentRequest);

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(paymentResponse);
    }
}
