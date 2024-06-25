package com.technical.challenge.financialtransactions.repository.statementCustom;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;

import java.time.LocalDate;
import java.util.List;

public interface StatementRepositoryCustom {

    List<Statement> findStatementsByCriteria(LocalDate paymentDate, Status status);

}
