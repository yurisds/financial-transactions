package com.technical.challenge.financialtransactions.repository;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.transactionCustom.TransactionRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>, TransactionRepositoryCustom {

    Optional<Transaction> findByTransactionId(String transactionId);
    List<Transaction> findAllByDescription(String description);
    List<Transaction> findAllByPaymentMethod(PaymentMethod paymentMethod);
    List<Transaction> findAllByCustomerName(String customerName);


}
