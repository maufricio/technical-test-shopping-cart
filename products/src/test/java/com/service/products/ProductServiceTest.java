package com.service.products;

import com.service.products.client.FakeStoreClient;
import com.service.products.dto.ProductResponse;
import com.service.products.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private FakeStoreClient productClient; // mocks the FakeStoreClient

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnAllProducts() {

        ProductResponse product = new ProductResponse();
        product.setId(1L);
        product.setTitle("Test Product");

        when(productClient.getAllProducts())
                .thenReturn(List.of(product));

        List<ProductResponse> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productClient).getAllProducts(); // make sure the client was called
    }
}
