package com.technical.challenge.financialtransactions.resource.response;

import com.technical.challenge.financialtransactions.model.PaymentMethod;

public class TransactionResponse {

    private String merchantCode;
    private Double amount;
    private String description;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cvv;

    public String getMerchantCode() {
        return merchantCode;
    }

    public TransactionResponse setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionResponse setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransactionResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TransactionResponse setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public TransactionResponse setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public TransactionResponse setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public TransactionResponse setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public TransactionResponse setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
}
