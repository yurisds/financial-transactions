package com.technical.challenge.financialtransactions.repository.statementCustom;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StatementRepositoryCustomImpl implements StatementRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public StatementRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Statement> findStatementsByCriteria(LocalDate paymentDate, Status status) {
        var query = new Query();

        if (Objects.nonNull(paymentDate)) {
            LocalDateTime startOfDay = paymentDate.atStartOfDay();
            LocalDateTime endOfDay = paymentDate.atTime(23, 59, 59);
            query.addCriteria(Criteria.where("paymentDate").gte(startOfDay).lte(endOfDay));
        }
        if (Objects.nonNull(status)) {
            query.addCriteria(Criteria.where("status").is(status));
        }

        return mongoTemplate.find(query, Statement.class);
    }
}
