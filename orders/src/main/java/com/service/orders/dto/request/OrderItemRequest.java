package com.service.orders.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class OrderItemRequest {
    @NotNull(message = "There must be a product ID to select")
    private Long productId;

    @NotNull(message = "Quantity of product must not be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
}
