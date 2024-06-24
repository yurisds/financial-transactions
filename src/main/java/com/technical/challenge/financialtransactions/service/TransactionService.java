package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.mapper.TransactionMapper;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.TransactionRepository;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final StatementService statementService;

    public TransactionService(TransactionRepository transactionRepository, StatementService statementService
    ) {
        this.transactionRepository = transactionRepository;
        this.statementService = statementService;
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String customerName) {
        return transactionRepository.findTransactionsByCriteria(description, paymentMethod, customerName);
    }

    public Transaction findByTransactionId(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId).orElseThrow(() -> new ServiceException(TRANSACTION_NOT_FOUND));
    }

    public Transaction createTransaction(TransactionRequest transactionRequest) {
        try {
            var transaction = transactionRepository.save(TransactionMapper.toTransaction(transactionRequest));
            statementService.createStatement(transaction);

            return transaction;
        } catch (Exception ex) {
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

}
