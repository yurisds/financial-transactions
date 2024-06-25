package com.technical.challenge.financialtransactions.resource.response;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Status;

import java.time.LocalDateTime;

public class StatementResponse {

    private String id;
    private String merchantCode;
    private Double amount;
    private Double finalAmount;
    private PaymentMethod paymentMethod;
    private Status status;
    private String cardNumber;
    private String cardHolderName;
    private LocalDateTime paymentDate;

    public String getId() {
        return id;
    }

    public StatementResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public StatementResponse setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public StatementResponse setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public StatementResponse setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public StatementResponse setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public StatementResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public StatementResponse setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public StatementResponse setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public StatementResponse setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }
}
