package com.service.orders.client.fallback;

import com.service.orders.client.PaymentClient;
import com.service.orders.client.dto.request.PaymentRequest;
import com.service.orders.client.dto.response.PaymentResponse;

public class PaymentClientFallback implements PaymentClient {

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        throw new RuntimeException("Payment service unavailable");
    }
}
