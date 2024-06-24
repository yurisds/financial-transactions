package com.technical.challenge.financialtransactions.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public enum BaseException {

    TRANSACTION_NOT_FOUND ("FT-001", NOT_FOUND, "Transação não encontrada"),
    UNKNOWN_ERROR ("FT-500", INTERNAL_SERVER_ERROR, "Erro desconhecido");

    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    BaseException(String code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
