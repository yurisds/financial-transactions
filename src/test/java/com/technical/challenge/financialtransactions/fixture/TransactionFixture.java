package com.technical.challenge.financialtransactions.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;

public class TransactionFixture implements TemplateLoader {

    public static final String TRANSACTION_FIXTURE_ONE = "TRANSACTION_FIXTURE_ONE";
    public static final String TRANSACTION_FIXTURE_TWO = "TRANSACTION_FIXTURE_TWO";
    public static final String TRANSACTION_FIXTURE_THREE = "TRANSACTION_FIXTURE_THREE";
    public static final String TRANSACTION_FIXTURE_FOUR = "TRANSACTION_FIXTURE_FOUR";

    @Override
    public void load() {

        Fixture.of(Transaction.class).addTemplate(TRANSACTION_FIXTURE_ONE, new Rule() {{
            add("merchantCode", "3f5a45b2-135a-4660-aef1-0f0bbe5ea8df");
            add("amount", 100.0);
            add("description", "Mercearia do Johnson");
            add("paymentMethod", PaymentMethod.DEBIT);
            add("cardNumber", "0000 0000 0000 0000");
            add("cardHolderName", "JOSUE B MIRANDA");
            add("cardExpirationDate", "01/29");
            add("cvv", "120");
        }});

        Fixture.of(Transaction.class).addTemplate(TRANSACTION_FIXTURE_TWO, new Rule() {{
            add("merchantCode", "3f5a45b2-135a-4660-aef1-0f0bbe5ea8df");
            add("amount", 115.0);
            add("description", "Mercearia do Johnson");
            add("paymentMethod", PaymentMethod.CREDIT);
            add("cardNumber", "0000 0000 0000 0000");
            add("cardHolderName", "ANDRE J DAMACENO");
            add("cardExpirationDate", "04/25");
            add("cvv", "214");
        }});

        Fixture.of(Transaction.class).addTemplate(TRANSACTION_FIXTURE_THREE, new Rule() {{
            add("merchantCode", "3f5a45b2-135a-4660-aef1-0f0bbe5ea8df");
            add("amount", 250.0);
            add("description", "Mercearia do Johnson");
            add("paymentMethod", PaymentMethod.DEBIT);
            add("cardNumber", "0000 0000 0000 0000");
            add("cardHolderName", "TOBIAS C ABRAAO");
            add("cardExpirationDate", "03/28");
            add("cvv", "445");
        }});

        Fixture.of(Transaction.class).addTemplate(TRANSACTION_FIXTURE_FOUR, new Rule() {{
            add("merchantCode", "3f5a45b2-135a-4660-aef1-0f0bbe5ea8df");
            add("amount", 150.0);
            add("description", "Mercearia do Johnson");
            add("paymentMethod", PaymentMethod.CREDIT);
            add("cardNumber", "0000 0000 0000 0000");
            add("cardHolderName", "LIGIA J CARDOSO");
            add("cardExpirationDate", "05/30");
            add("cvv", "289");
        }});
    }
}
