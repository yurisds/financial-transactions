package com.technical.challenge.financialtransactions.mapper;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import com.technical.challenge.financialtransactions.resource.response.TransactionResponse;

import java.util.Objects;

import static com.technical.challenge.financialtransactions.exception.BaseException.INVALID_CARD_NUMBER;

public class TransactionMapper {

    public static Transaction toTransaction(TransactionRequest request) {

        return new Transaction()
                .setMerchantCode(request.getMerchantCode())
                .setAmount(request.getAmount())
                .setDescription(request.getDescription())
                .setPaymentMethod(request.getPaymentMethod())
                .setCardNumber(maskCardNumber(request.getCardNumber()))
                .setCardHolderName(request.getCardHolderName())
                .setCardExpirationDate(request.getCardExpirationDate())
                .setCvv(request.getCvv());
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {

        return new TransactionResponse()
                .setMerchantCode(transaction.getMerchantCode())
                .setAmount(transaction.getAmount())
                .setDescription(transaction.getDescription())
                .setPaymentMethod(transaction.getPaymentMethod())
                .setCardNumber(maskCardNumber(transaction.getCardNumber()))
                .setCardHolderName(transaction.getCardHolderName())
                .setCardExpirationDate(transaction.getCardExpirationDate())
                .setCvv(transaction.getCvv());
    }

    private static String maskCardNumber(String cardNumber) {
        if (Objects.isNull(cardNumber) || cardNumber.length() < 16) {
            throw new ServiceException(INVALID_CARD_NUMBER);
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
