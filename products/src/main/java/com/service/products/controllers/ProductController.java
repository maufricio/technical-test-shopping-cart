package com.service.products.controllers;

import com.service.products.dto.ApiResponse;
import com.service.products.dto.ProductResponse;
import com.service.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(path = "/products/", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponse> prods = this.productService.getAllProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(HttpStatus.OK, "Data retrieved", prods));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK, "Product retrieved", product)
        );
    }
}
