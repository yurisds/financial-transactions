package com.technical.challenge.financialtransactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        var errorResponse = new ErrorResponse()
                .setCode(ex.getBaseException().getCode())
                .setMessage(ex.getBaseException().getDescription())
                .setHttpStatusCode(ex.getBaseException().getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getBaseException().getHttpStatus());
    }
}
