package com.service.orders.dto.response;

import com.service.orders.constants.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {

    private Long id;
    private OrderStatus status;
    private Double total;
    private Long customerId;
}
