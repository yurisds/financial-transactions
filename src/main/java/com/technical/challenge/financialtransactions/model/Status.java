package com.technical.challenge.financialtransactions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    PAID("paid"),
    WAITING_FUNDS("waiting_funds");

    private final String value;

    Status(String value) {
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
    public static Status fromString(String value) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }
}
