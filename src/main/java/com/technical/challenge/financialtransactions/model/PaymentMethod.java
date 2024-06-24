package com.technical.challenge.financialtransactions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {

    DEBIT("debit"),
    CREDIT("credit");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static PaymentMethod fromString(String value) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.value.equalsIgnoreCase(value)) {
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("Invalid payment method: " + value);
    }
}
