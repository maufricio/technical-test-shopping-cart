package com.service.orders.client.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400:
                return new RuntimeException("Bad request to external service");
            case 404:
                return new RuntimeException("Resource not found in external service");
            case 500:
                return new RuntimeException("External server error");
            default:
                return new RuntimeException("Unknown error from external service");
        }
    }
}
