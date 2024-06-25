package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;

import java.time.LocalDate;
import java.util.List;

public interface StatementService {

    void createStatement(Transaction transaction);
    Statement findStatementByTransactionId(String transactionId);
    List<Statement> findStatementsByCriteria(LocalDate paymentDate, Status status);
    void updateStatusByCriteria (LocalDate paymentDate, Status status);
}
