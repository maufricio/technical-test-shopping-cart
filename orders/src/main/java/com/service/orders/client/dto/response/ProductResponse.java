package com.service.orders.client.dto.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String title;
    private Double price;
}
