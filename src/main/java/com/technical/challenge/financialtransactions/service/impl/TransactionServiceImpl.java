package com.technical.challenge.financialtransactions.service.impl;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.mapper.TransactionMapper;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.TransactionRepository;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import com.technical.challenge.financialtransactions.resource.response.TransactionResponse;
import com.technical.challenge.financialtransactions.service.StatementService;
import com.technical.challenge.financialtransactions.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final StatementService statementService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, StatementService statementService
    ) {
        this.transactionRepository = transactionRepository;
        this.statementService = statementService;
    }

    @Override
    public List<Transaction> findAll() {
        LOGGER.info("m=findAll stage=init");
        try {
            return transactionRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error("m=findAll stage=error message={}", ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public List<TransactionResponse> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String cardHolderName) {
        LOGGER.info("m=findTransactionsByCriteria stage=init description={} paymentMethod={} cardHolderName={}", description, paymentMethod, cardHolderName);
        try {
            return transactionRepository.findTransactionsByCriteria(description, paymentMethod, cardHolderName)
                    .stream().map(TransactionMapper::toTransactionResponse).toList();
        } catch (Exception ex) {
            LOGGER.error("m=findTransactionsByCriteria stage=error message={}", ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public Transaction findByTransactionId(String transactionId) {
        LOGGER.info("m=findByTransactionId stage=init transactionId={}", transactionId);
        try {
            return transactionRepository.findByTransactionId(transactionId).orElseThrow(() -> new ServiceException(TRANSACTION_NOT_FOUND));
        } catch (ServiceException ex) {
            LOGGER.error("m=findByTransactionId stage=error message={}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("m=findByTransactionId stage=error message={}", ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    @Override
    public Transaction createTransaction(TransactionRequest transactionRequest) {
        LOGGER.info("m=createTransaction stage=init transactionRequest={}", transactionRequest);

        try {
            var transaction = transactionRepository.save(TransactionMapper.toTransaction(transactionRequest));
            statementService.createStatement(transaction);

            return transaction;
        } catch (Exception ex) {
            LOGGER.error("m=createTransaction stage=error message={}", ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

}
