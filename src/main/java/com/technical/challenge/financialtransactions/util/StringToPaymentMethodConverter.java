package com.technical.challenge.financialtransactions.util;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPaymentMethodConverter implements Converter<String, PaymentMethod> {

    @Override
    public PaymentMethod convert(String source) {
        return PaymentMethod.fromString(source);
    }
}
