package com.service.orders.controllers;


import com.service.orders.dto.request.CreateOrderRequest;
import com.service.orders.dto.response.OrderResponse;
import com.service.orders.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
          @Valid @RequestBody CreateOrderRequest request
    ) {

        OrderResponse order = this.orderService.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
