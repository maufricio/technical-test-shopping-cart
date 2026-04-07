package com.service.products.services;

import com.service.products.client.FakeStoreClient;
import com.service.products.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final FakeStoreClient fakeStoreClient;

    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products");
        return this.fakeStoreClient.getAllProducts();
    }

    public ProductResponse getProductById(@PathVariable Long id) {
        log.info("Getting product by id: {}", id);
        return this.fakeStoreClient.getProductById(id);
    }
}
