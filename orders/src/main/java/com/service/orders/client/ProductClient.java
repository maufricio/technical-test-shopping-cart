package com.service.orders.client;

import com.service.orders.client.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
