package com.technical.challenge.financialtransactions.repository;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.repository.statementCustom.StatementRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatementRepository extends MongoRepository<Statement, String>, StatementRepositoryCustom {

    Optional<Statement> findByTransactionId(String transactionId);
}
