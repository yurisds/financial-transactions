package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();
    List<Transaction> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String customerName);
    Transaction findByTransactionId(String transactionId);
    Transaction createTransaction(TransactionRequest transactionRequest);

}
