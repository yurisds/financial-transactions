package com.technical.challenge.financialtransactions.exception;

public class ServiceException extends RuntimeException {

    private final BaseException baseException;

    public ServiceException(BaseException baseException) {
        this.baseException = baseException;
    }

    public BaseException getBaseException() {
        return baseException;
    }
}
