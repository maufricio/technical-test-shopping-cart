package com.service.orders.client.dto.response;

import lombok.Data;

@Data
public class PaymentResponse {

    private String status; // APPROVED, REJECTED
}
