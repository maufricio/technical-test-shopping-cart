package com.service.orders;

import com.service.orders.client.PaymentClient;
import com.service.orders.client.ProductClient;
import com.service.orders.client.dto.response.PaymentResponse;
import com.service.orders.client.dto.response.ProductResponse;
import com.service.orders.constants.OrderStatus;
import com.service.orders.dto.request.CreateOrderRequest;
import com.service.orders.dto.request.OrderItemRequest;
import com.service.orders.dto.response.OrderResponse;
import com.service.orders.entities.Customer;
import com.service.orders.repositories.CustomerRepository;
import com.service.orders.repositories.OrderRepository;
import com.service.orders.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductClient productClient;

    @Mock
    private PaymentClient paymentClient;

    @InjectMocks
    private OrderService orderService;

    // this test makes sure that the order is created successfully
    @Test
    void shouldCreateOrderSuccessfully() {

        Long customerId = 1L;

        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId(customerId);

        OrderItemRequest item = new OrderItemRequest();
        item.setProductId(10L);
        item.setQuantity(2);

        request.setItems(List.of(item));

        Customer customer = new Customer();
        customer.setId(customerId);

        ProductResponse product = new ProductResponse();
        product.setId(10L);
        product.setPrice(50.0);

        PaymentResponse payment = new PaymentResponse();
        payment.setStatus(OrderStatus.APPROVED);

        when(customerRepository.findById(customerId))
                .thenReturn(Optional.of(customer));

        when(productClient.getProductById(10L))
                .thenReturn(product);

        when(paymentClient.processPayment(any()))
                .thenReturn(payment);

        when(orderRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        OrderResponse response = orderService.createOrder(request);

        assertNotNull(response);
        assertEquals(customerId, response.getCustomerId());

        verify(orderRepository, times(2)).save(any());
        verify(productClient).getProductById(10L);
        verify(paymentClient).processPayment(any());
    }
}
