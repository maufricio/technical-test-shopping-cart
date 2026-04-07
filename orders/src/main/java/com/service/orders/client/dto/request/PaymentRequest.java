package com.service.orders.client.dto.request;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long orderId;
    private Double amount;
}
