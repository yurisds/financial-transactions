package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.factory.PaymentStrategyFactory;
import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.StatementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;

@Service
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public StatementServiceImpl(StatementRepository statementRepository,
                                PaymentStrategyFactory paymentStrategyFactory) {
        this.statementRepository = statementRepository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    @Override
    public void createStatement(Transaction transaction) {
        try {
            var statement = new Statement().setTransactionId(transaction.getTransactionId());
            paymentStrategyFactory.getStrategy(transaction.getPaymentMethod()).processPayment(transaction, statement);
            statementRepository.save(statement);

        } catch (Exception ex) {
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public Statement findStatementByTransactionId(String transactionId) {
        try {
            return statementRepository.findByTransactionId(transactionId).orElseThrow(() -> new ServiceException(TRANSACTION_NOT_FOUND));

        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public List<Statement> findStatementsByCriteria(LocalDate paymentDate, Status status) {
        try {
            return statementRepository.findStatementsByCriteria(paymentDate, status);
        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }
}
