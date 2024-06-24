package com.technical.challenge.financialtransactions.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class Statement {

    @Id
    private String statementId;
    @Indexed(unique = true)
    private String transactionId;
    private Double amount;
    private Status status;
    private LocalDateTime paymentDate;

    public Statement() {
        this.statementId = UUID.randomUUID().toString();
    }

    public String getStatementId() {
        return statementId;
    }

    public Statement setStatementId(String statementId) {
        this.statementId = statementId;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Statement setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Statement setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Statement setStatus(Status status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Statement setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }
}
