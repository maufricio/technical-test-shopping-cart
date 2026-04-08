package com.service.orders.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    @NotEmpty(message = "Order items must not be empty")
    private List<@Valid OrderItemRequest> items;
}
