package com.technical.challenge.financialtransactions.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Transaction {

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

    public Transaction() {
        this.transactionId = UUID.randomUUID().toString();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public Transaction setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Transaction setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Transaction setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Transaction setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public Transaction setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
        return this;
    }

    public String getCvv() {
        return cvv;
    }

    public Transaction setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
}
