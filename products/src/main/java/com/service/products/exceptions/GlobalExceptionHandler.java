package com.service.products.exceptions;

import com.service.products.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse handleGenericException(Exception ex) {
        return new ApiResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleRuntimeException(RuntimeException ex) {
        return new ApiResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ApiResponse handleNullPointerException(NullPointerException ex) {
        return new ApiResponse(
                HttpStatus.BAD_REQUEST,
                "A null value was encountered: " + ex.getMessage(),
                null
        );
    }


}
