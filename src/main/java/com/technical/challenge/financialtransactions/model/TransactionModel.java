package com.technical.challenge.financialtransactions.model;

import org.springframework.data.annotation.Id;

public class TransactionModel {

    @Id
    private String transactionId;
    private String merchantCode;
    private Double amount;
    private String description;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String customerName;
    private String cardExpirationDate;
    private String cvv;

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionModel setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public TransactionModel setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionModel setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TransactionModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TransactionModel setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public TransactionModel setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TransactionModel setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public TransactionModel setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public TransactionModel setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
}
