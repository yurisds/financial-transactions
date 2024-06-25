package com.technical.challenge.financialtransactions.config;

import com.technical.challenge.financialtransactions.util.StringToPaymentMethodConverter;
import com.technical.challenge.financialtransactions.util.StringToStatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToStatusConverter stringToStatusConverter;
    private final StringToPaymentMethodConverter stringToPaymentMethodConverter;

    public WebConfig(StringToStatusConverter stringToStatusConverter,
                     StringToPaymentMethodConverter stringToPaymentMethodConverter) {
        this.stringToStatusConverter = stringToStatusConverter;
        this.stringToPaymentMethodConverter = stringToPaymentMethodConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToStatusConverter);
        registry.addConverter(stringToPaymentMethodConverter);
    }
}