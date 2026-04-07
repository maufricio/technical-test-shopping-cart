package com.service.products.client;

import com.service.products.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "fakeStoreClient", url = "https://fakestoreapi.com")
public interface FakeStoreClient {

    // Brings in the whole list of products
    @GetMapping("/products")
    List<ProductResponse> getAllProducts();

    // Takes one product filtered by the id
    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
