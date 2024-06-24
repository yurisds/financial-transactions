package com.technical.challenge.financialtransactions.repository;

import com.technical.challenge.financialtransactions.model.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatementRepository extends MongoRepository<Statement, String> {

    Optional<Statement> findByTransactionId(String transactionId);
}
