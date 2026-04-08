package com.service.payments.services;

import com.service.payments.constants.PaymentStatus;
import com.service.payments.dtos.request.PaymentRequest;
import com.service.payments.dtos.response.PaymentResponse;
import com.service.payments.entities.Payment;
import com.service.payments.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {

        PaymentStatus status = simulatePayment(paymentRequest.getAmount());

        String transactionId = UUID.randomUUID().toString();

        Payment payment = Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .status(status)
                .transactionId(transactionId)
                .build();

        Payment savedPayment = this.paymentRepository.save(payment);

        return PaymentResponse.builder()
                .id(savedPayment.getId())
                .orderId(savedPayment.getOrderId())
                .amount(savedPayment.getAmount())
                .status(savedPayment.getStatus())
                .transactionId(savedPayment.getTransactionId())
                .build();
    }

    private PaymentStatus simulatePayment(Double amount) {
        // Lets put an amount limited to approve the payment: the user has $100.00 in his bank account
        if (amount <= 100) {
            return PaymentStatus.APPROVED;
        } else {
            return PaymentStatus.REJECTED;
        }
    }
}
