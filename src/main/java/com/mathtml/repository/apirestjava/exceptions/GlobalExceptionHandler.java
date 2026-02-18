package com.mathtml.repository.apirestjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(
                        new ErrorResponse(
                                ex.getErrorCode(),
                                ex.getMessage()
                        )
                );
    }
}
