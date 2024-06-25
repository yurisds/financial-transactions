package com.technical.challenge.financialtransactions.resource.request;

import com.technical.challenge.financialtransactions.model.PaymentMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransactionRequest {

    @NotBlank
    private String merchantCode;

    @NotNull
    private Double amount;

    @NotBlank
    private String description;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardHolderName;

    @NotBlank
    private String cardExpirationDate;

    @NotBlank
    @Size(min = 3, max = 3)
    private String cvv;

    public String getMerchantCode() {
        return merchantCode;
    }

    public TransactionRequest setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionRequest setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransactionRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TransactionRequest setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public TransactionRequest setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public TransactionRequest setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public TransactionRequest setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public TransactionRequest setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
}
