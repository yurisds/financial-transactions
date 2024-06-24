package com.technical.challenge.financialtransactions.strategy;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;

import java.time.LocalDateTime;

public class CreditPaymentStrategy implements PaymentStrategy{

    private final double PROCESSING_FEE = 0.05;

    @Override
    public void processPayment(Transaction transaction, Statement statement) {
        statement.setStatus(Status.WAITING_FUNDS)
                .setPaymentDate(LocalDateTime.now().plusDays(30))
                .setAmount(calculateAmount(transaction.getAmount()));
    }

    private double calculateAmount(double amount) {
        return amount * (1 - PROCESSING_FEE);
    }
}
