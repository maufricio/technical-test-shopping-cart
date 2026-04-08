package com.service.orders.client.dto.response;

import com.service.orders.constants.OrderStatus;
import lombok.Data;

@Data
public class PaymentResponse {

    private OrderStatus status; // APPROVED, REJECTED
}
