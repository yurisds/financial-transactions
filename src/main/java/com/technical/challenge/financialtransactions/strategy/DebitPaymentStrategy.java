package com.technical.challenge.financialtransactions.strategy;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;

import java.time.LocalDateTime;

public class DebitPaymentStrategy implements PaymentStrategy{

    private final double PROCESSING_FEE = 0.03;

    @Override
    public void processPayment(Transaction transaction, Statement statement) {
        statement.setStatus(Status.PAID)
                .setPaymentDate(LocalDateTime.now())
                .setAmount(calculateAmount(transaction.getAmount()));
    }

    private double calculateAmount(double amount) {
        return amount * (1 - PROCESSING_FEE);
    }
}
