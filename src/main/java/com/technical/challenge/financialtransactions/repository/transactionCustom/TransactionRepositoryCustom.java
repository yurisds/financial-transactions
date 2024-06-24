package com.technical.challenge.financialtransactions.repository.transactionCustom;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;

import java.util.List;

public interface TransactionRepositoryCustom {

    List<Transaction> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String customerName);

}
