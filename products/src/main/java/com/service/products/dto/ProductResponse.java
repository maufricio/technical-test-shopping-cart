package com.service.products.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
}
