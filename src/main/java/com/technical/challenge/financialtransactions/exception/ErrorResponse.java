package com.technical.challenge.financialtransactions.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String code;
    private String message;
    private HttpStatus httpStatusCode;

    public String getCode() {
        return code;
    }

    public ErrorResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public ErrorResponse setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }
}
