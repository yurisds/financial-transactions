package com.technical.challenge.financialtransactions.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;

import java.time.LocalDateTime;

public class StatementFixture implements TemplateLoader {

    public static final String STATEMENT_FIXTURE_ONE = "STATEMENT_FIXTURE_ONE";
    public static final String STATEMENT_FIXTURE_TWO = "STATEMENT_FIXTURE_TWO";
    public static final String STATEMENT_FIXTURE_THREE = "STATEMENT_FIXTURE_THREE";
    public static final String STATEMENT_FIXTURE_FOUR = "STATEMENT_FIXTURE_FOUR";

    @Override
    public void load() {
        Fixture.of(Statement.class).addTemplate(STATEMENT_FIXTURE_ONE, new Rule() {{
            add("amount", 97.0);
            add("status", Status.PAID);
            add("paymentDate", LocalDateTime.of(2024, 6, 18, 12, 48, 5));
        }});

        Fixture.of(Statement.class).addTemplate(STATEMENT_FIXTURE_TWO, new Rule() {{
            add("amount", 109.25);
            add("status", Status.WAITING_FUNDS);
            add("paymentDate", LocalDateTime.of(2024, 7, 18, 12, 50, 5));
        }});

        Fixture.of(Statement.class).addTemplate(STATEMENT_FIXTURE_THREE, new Rule() {{
            add("amount", 242.50);
            add("status", Status.PAID);
            add("paymentDate", LocalDateTime.of(2024, 6, 18, 12, 55, 5));
        }});

        Fixture.of(Statement.class).addTemplate(STATEMENT_FIXTURE_FOUR, new Rule() {{
            add("amount", 142.50);
            add("status", Status.WAITING_FUNDS);
            add("paymentDate", LocalDateTime.of(2024, 7, 18, 12, 48, 5));
        }});
    }
}