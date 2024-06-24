package com.technical.challenge.financialtransactions.factory;

import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.strategy.CreditPaymentStrategy;
import com.technical.challenge.financialtransactions.strategy.DebitPaymentStrategy;
import com.technical.challenge.financialtransactions.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

import static com.technical.challenge.financialtransactions.exception.BaseException.INCORRECT_PAYMENT_TYPE;

@Component
public class PaymentStrategyFactory {

    public PaymentStrategy getStrategy(PaymentMethod paymentMethod) {

        return switch (paymentMethod) {
            case DEBIT -> new DebitPaymentStrategy();
            case CREDIT -> new CreditPaymentStrategy();
            default -> throw new ServiceException(INCORRECT_PAYMENT_TYPE);
        };
    }
}
