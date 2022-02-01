package com.example.springbootsampleproject.handlers;

import com.example.springbootsampleproject.dtos.responses.GlobalErrorResponseInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public GlobalErrorResponseInfo handleIllegalArgumentException(final IllegalArgumentException error) {
        error.printStackTrace();

        GlobalErrorResponseInfo responseInfo = new GlobalErrorResponseInfo();
        return responseInfo;
    }
}
