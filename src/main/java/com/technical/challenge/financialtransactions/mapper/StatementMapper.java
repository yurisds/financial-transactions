package com.technical.challenge.financialtransactions.mapper;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;

public class StatementMapper {

    public static StatementResponse toStatementResponse(Statement statement, Transaction transaction) {

        return new StatementResponse()
                .setId(statement.getTransactionId())
                .setMerchantCode(transaction.getMerchantCode())
                .setAmount(transaction.getAmount())
                .setFinalAmount(statement.getAmount())
                .setPaymentMethod(transaction.getPaymentMethod())
                .setStatus(statement.getStatus())
                .setCardNumber(transaction.getCardNumber())
                .setCardHolderName(transaction.getCardHolderName())
                .setPaymentDate(statement.getPaymentDate());

    }
}
