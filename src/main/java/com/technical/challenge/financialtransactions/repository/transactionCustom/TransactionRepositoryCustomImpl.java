package com.technical.challenge.financialtransactions.repository.transactionCustom;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Objects;

public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public TransactionRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Transaction> findTransactionsByCriteria(String description, PaymentMethod paymentMethod, String cardHolderName) {
        var query = new Query();

        if (Objects.nonNull(description) && !description.isEmpty()) {
            query.addCriteria(Criteria.where("description").is(description));
        }
        if (Objects.nonNull(paymentMethod)) {
            query.addCriteria(Criteria.where("paymentMethod").is(paymentMethod));
        }
        if (Objects.nonNull(cardHolderName) && !cardHolderName.isEmpty()) {
            query.addCriteria(Criteria.where("cardHolderName").is(cardHolderName));
        }

        return mongoTemplate.find(query, Transaction.class);
    }
}
