package com.technical.challenge.financialtransactions.service;

import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;

import java.time.LocalDate;
import java.util.List;

public interface StatementTransactionService {

    StatementResponse getStatementWithTransaction(String transactionId);
    List<StatementResponse> getAllStatementWithTransaction(LocalDate paymentDate, Status status);

}
