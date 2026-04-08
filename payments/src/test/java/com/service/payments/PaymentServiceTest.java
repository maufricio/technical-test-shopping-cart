package com.service.payments;

import com.service.payments.constants.PaymentStatus;
import com.service.payments.dtos.request.PaymentRequest;
import com.service.payments.dtos.response.PaymentResponse;
import com.service.payments.repositories.PaymentRepository;
import com.service.payments.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void shouldApprovePaymentWhenAmountIsLessThanOrEqualTo100() {

        PaymentRequest request = new PaymentRequest();
        request.setOrderId(1L);
        request.setAmount(50.00);

        when(paymentRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PaymentResponse response = paymentService.processPayment(request);

        assertNotNull(response);
        assertEquals(PaymentStatus.APPROVED, response.getStatus());
    }

    @Test
    void shouldRejectPaymentWhenAmountIsGreaterThan100() {

        PaymentRequest request = new PaymentRequest();
        request.setOrderId(1L);
        request.setAmount(150.00);

        when(paymentRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PaymentResponse response = paymentService.processPayment(request);

        assertNotNull(response);
        assertEquals(PaymentStatus.REJECTED, response.getStatus());
    }
}
