package com.technical.challenge.financialtransactions.strategy;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Transaction;

public interface PaymentStrategy {

    void processPayment(Transaction transaction, Statement statement);
}
