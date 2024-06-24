package com.technical.challenge.financialtransactions.resource.request;

import com.technical.challenge.financialtransactions.model.PaymentMethod;

public class TransactionRequest {

    private String merchantCode;
    private Double amount;
    private String description;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String customerName;
    private String cardExpirationDate;
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

    public String getCustomerName() {
        return customerName;
    }

    public TransactionRequest setCustomerName(String customerName) {
        this.customerName = customerName;
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
