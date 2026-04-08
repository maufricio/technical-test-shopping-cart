package com.service.orders.client.fallback;

import com.service.orders.client.ProductClient;
import com.service.orders.client.dto.response.ProductResponse;

import java.util.List;

public class ProductClientFallback implements ProductClient {

    @Override
    public List<ProductResponse> getAllProducts() {
        throw new RuntimeException("Product service unavailable");
    }

    @Override
    public ProductResponse getProductById(Long id) {
        throw new RuntimeException("Product service unavailable");
    }
}
