package com.academy.backend.exception.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BaseException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getErrorCode());
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }
}
