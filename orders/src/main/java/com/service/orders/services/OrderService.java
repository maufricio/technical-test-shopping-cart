package com.service.orders.services;

import com.service.orders.client.PaymentClient;
import com.service.orders.client.ProductClient;
import com.service.orders.client.dto.request.PaymentRequest;
import com.service.orders.client.dto.response.PaymentResponse;
import com.service.orders.client.dto.response.ProductResponse;
import com.service.orders.constants.OrderStatus;
import com.service.orders.dto.request.CreateOrderRequest;
import com.service.orders.dto.request.OrderItemRequest;
import com.service.orders.dto.response.OrderResponse;
import com.service.orders.entities.Customer;
import com.service.orders.entities.Order;
import com.service.orders.entities.OrderDetail;
import com.service.orders.repositories.CustomerRepository;
import com.service.orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    public OrderResponse createOrder(CreateOrderRequest request){

        // Finding the customer by the id provided in the request
        Customer customer = this.customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Creating the order
        List<OrderDetail> details = new ArrayList<>();
        double total = 0.0;

        // looping through the items in the request
        for(OrderItemRequest item : request.getItems()) {

            ProductResponse product = this.productClient.getProductById(item.getProductId());

            double price = product.getPrice();
            double subtotal = price * item.getQuantity();

            total += subtotal;

            // Creating the order detail entity instance for each item
            OrderDetail detail = OrderDetail.builder()
                    .productId(product.getId())
                    .quantity(item.getQuantity())
                    .price(price)
                    .build();

            details.add(detail);
        }

        Order order = Order.builder()
                .customer(customer)
                .status(OrderStatus.PENDING)
                .total(total)
                .items(details)
                .build();

        details.forEach(d -> d.setOrder(order)); // setting the order to each detail

        Order savedOrder = this.orderRepository.save(order); // saving the order

        // Making the payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(savedOrder.getId());
        paymentRequest.setAmount(total);

        PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest); // calling the payment service

        savedOrder.setStatus(paymentResponse.getStatus());

        Order updatedOrder = this.orderRepository.save(savedOrder); // updating the order status to "APPROVED" or "REJECTED"

        return OrderResponse.builder()
                .id(updatedOrder.getId())
                .status(updatedOrder.getStatus())
                .total(updatedOrder.getTotal())
                .customerId(updatedOrder.getCustomer().getId())
                .build();
    }
}
