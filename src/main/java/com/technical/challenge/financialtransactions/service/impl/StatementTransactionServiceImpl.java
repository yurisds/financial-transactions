package com.technical.challenge.financialtransactions.service.impl;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.mapper.StatementMapper;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;
import com.technical.challenge.financialtransactions.service.StatementService;
import com.technical.challenge.financialtransactions.service.StatementTransactionService;
import com.technical.challenge.financialtransactions.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;

@Service
public class StatementTransactionServiceImpl implements StatementTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatementTransactionServiceImpl.class);
    private final StatementService statementService;
    private final TransactionService transactionService;

    public StatementTransactionServiceImpl(StatementService statementService, TransactionService transactionService) {
        this.statementService = statementService;
        this.transactionService = transactionService;
    }

    public StatementResponse getStatementWithTransaction(String transactionId) {

        LOGGER.info("m=getStatementWithTransaction stage=init transactionId={}", transactionId);
        try {
            var statement = statementService.findStatementByTransactionId(transactionId);
            var transaction = transactionService.findByTransactionId(transactionId);

            return StatementMapper.toStatementResponse(statement, transaction);
        } catch (Exception ex) {
            LOGGER.error("m=getAllStatementWithTransaction stage=error message={}", ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

    public List<StatementResponse> getAllStatementWithTransaction(LocalDate paymentDate, Status status) {

        LOGGER.info("m=getAllStatementWithTransaction stage=init paymentDate={} status={}", paymentDate, status);
        try {
            List<StatementResponse> statementResponseList = new ArrayList<>();

            var statement = statementService.findStatementsByCriteria(paymentDate, status);

            LOGGER.info("m=getAllStatementWithTransaction statement={}", statement);
            statement.forEach(s -> {
                var transaction = transactionService.findByTransactionId(s.getTransactionId());
                statementResponseList.add(StatementMapper.toStatementResponse(s, transaction));
            });

            return statementResponseList;

        } catch (Exception ex) {
            LOGGER.error("m=getAllStatementWithTransaction stage=error message={}", ex.getMessage());
            throw new ServiceException(UNKNOWN_ERROR);
        }
    }

}
