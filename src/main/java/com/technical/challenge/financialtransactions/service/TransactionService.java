package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import com.technical.challenge.financialtransactions.resource.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();
    List<TransactionResponse> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String cardHolderName);
    Transaction findByTransactionId(String transactionId);
    Transaction createTransaction(TransactionRequest transactionRequest);

}
