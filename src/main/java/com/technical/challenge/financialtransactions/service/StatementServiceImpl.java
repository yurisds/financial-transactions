package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.factory.PaymentStrategyFactory;
import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.StatementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;

@Service
public class StatementServiceImpl implements StatementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementServiceImpl.class);

    private final StatementRepository statementRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public StatementServiceImpl(StatementRepository statementRepository,
                                PaymentStrategyFactory paymentStrategyFactory) {
        this.statementRepository = statementRepository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    @Override
    public void createStatement(Transaction transaction) {
        LOGGER.info("m=createStatement stage=init transactionId={}", transaction.getTransactionId());
        try {
            var statement = new Statement().setTransactionId(transaction.getTransactionId());
            paymentStrategyFactory.getStrategy(transaction.getPaymentMethod()).processPayment(transaction, statement);
            statementRepository.save(statement);

        } catch (Exception ex) {
            LOGGER.error("m=createStatement stage=error transactionId={} message={}", transaction.getTransactionId(), ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public Statement findStatementByTransactionId(String transactionId) {
        LOGGER.info("m=findStatementByTransactionId stage=init transactionId={}", transactionId);
        try {
            return statementRepository.findByTransactionId(transactionId).orElseThrow(() -> new ServiceException(TRANSACTION_NOT_FOUND));
        } catch (ServiceException ex) {
            LOGGER.error("m=findStatementByTransactionId stage=error transactionId={} message={}", transactionId, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("m=findStatementByTransactionId stage=error transactionId={} message={}", transactionId, ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public List<Statement> findStatementsByCriteria(LocalDate paymentDate, Status status) {
        LOGGER.info("m=findStatementsByCriteria stage=init paymentDate={} status={}", paymentDate, status);
        try {
            return statementRepository.findStatementsByCriteria(paymentDate, status);
        } catch (ServiceException ex) {
            LOGGER.error("m=findStatementsByCriteria stage=error paymentDate={} status={} message={}", paymentDate, status, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("m=findStatementsByCriteria stage=error paymentDate={} status={} message={}", paymentDate, status, ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void updateStatusByCriteria(LocalDate paymentDate, Status status) {
        LOGGER.info("m=updateStatusByCriteria stage=init paymentDate={} status={}", paymentDate, status);
        try {
            var statement = statementRepository.findStatementsByCriteria(paymentDate, status);
            statement.forEach(
                    s -> {
                        s.setStatus(Status.PAID);
                        statementRepository.save(s);
                    }
            );
        } catch (ServiceException ex) {
            LOGGER.error("m=updateStatusByCriteria stage=error paymentDate={} status={} message={}", paymentDate, status, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("m=updateStatusByCriteria stage=error paymentDate={} status={} message={}", paymentDate, status, ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }
}
