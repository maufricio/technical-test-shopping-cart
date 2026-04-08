package com.service.payments.dtos.response;

import com.service.payments.constants.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

    private Long id;
    private Long orderId;
    private Double amount;
    private PaymentStatus status;
    private String transactionId;

}
