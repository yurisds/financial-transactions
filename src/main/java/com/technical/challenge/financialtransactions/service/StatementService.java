package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.factory.PaymentStrategyFactory;
import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.StatementRepository;
import org.springframework.stereotype.Service;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;

@Service
public class StatementService {

    private final StatementRepository statementRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public StatementService(StatementRepository statementRepository, PaymentStrategyFactory paymentStrategyFactory) {
        this.statementRepository = statementRepository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    public void createStatement(Transaction transaction) {
        try {
            var statement = new Statement().setTransactionId(transaction.getTransactionId());
            paymentStrategyFactory.getStrategy(transaction.getPaymentMethod()).processPayment(transaction, statement);
            statementRepository.save(statement);

        } catch (Exception ex) {
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    public Statement getStatementByTransactionId(String transactionId) {
        try {
            return statementRepository.findByTransactionId(transactionId).orElseThrow(() -> new ServiceException(TRANSACTION_NOT_FOUND));

        } catch (Exception ex) {
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }
}
