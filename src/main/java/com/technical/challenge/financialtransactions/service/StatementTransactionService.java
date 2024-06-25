package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.mapper.StatementMapper;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;
import org.springframework.stereotype.Service;

@Service
public class StatementTransactionService {

    private final StatementService statementService;
    private final TransactionService transactionService;

    public StatementTransactionService(StatementService statementService, TransactionService transactionService) {
        this.statementService = statementService;
        this.transactionService = transactionService;
    }

    public StatementResponse getStatementWithTransaction(String transactionId) {
        var statement = statementService.findStatementByTransactionId(transactionId);
        var transaction = transactionService.findByTransactionId(transactionId);

        return StatementMapper.toStatementResponse(statement, transaction);
    }

}
