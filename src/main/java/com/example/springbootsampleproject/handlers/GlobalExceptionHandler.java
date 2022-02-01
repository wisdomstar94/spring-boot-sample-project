package com.example.springbootsampleproject.handlers;

import com.example.springbootsampleproject.dtos.responses.GlobalErrorResponseInfo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalErrorResponseInfo handleIllegalArgumentException(final IllegalArgumentException error) {
        error.printStackTrace();

        GlobalErrorResponseInfo responseInfo = new GlobalErrorResponseInfo();
        responseInfo.setCode(error.hashCode());
        responseInfo.setMsg(error.getMessage());
        return responseInfo;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalErrorResponseInfo handleDataIntegrityViolationException(final DataIntegrityViolationException error) {
        error.printStackTrace();

        GlobalErrorResponseInfo responseInfo = new GlobalErrorResponseInfo();
        responseInfo.setCode(error.hashCode());
        responseInfo.setMsg(error.getMessage());
        return responseInfo;
    }
}
