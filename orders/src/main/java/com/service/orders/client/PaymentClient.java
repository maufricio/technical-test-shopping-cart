package com.service.orders.client;

import com.service.orders.client.dto.request.PaymentRequest;
import com.service.orders.client.dto.response.PaymentResponse;
import com.service.orders.client.fallback.PaymentClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "http://localhost:8082",
        fallback = PaymentClientFallback.class
)
public interface PaymentClient {

    @PostMapping("/payments")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}
