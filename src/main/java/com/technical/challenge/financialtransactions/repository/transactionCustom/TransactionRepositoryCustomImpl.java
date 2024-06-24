package com.technical.challenge.financialtransactions.repository.transactionCustom;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public TransactionRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Transaction> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String customerName) {
        var query = new Query();

        if (description != null && !description.isEmpty()) {
            query.addCriteria(Criteria.where("description").is(description));
        }
        if (paymentMethod != null) {
            query.addCriteria(Criteria.where("paymentMethod").is(paymentMethod));
        }
        if (customerName != null && !customerName.isEmpty()) {
            query.addCriteria(Criteria.where("customerName").is(customerName));
        }

        return mongoTemplate.find(query, Transaction.class);
    }
}
