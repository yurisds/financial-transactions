package com.technical.challenge.financialtransactions.service;

import br.com.six2six.fixturefactory.Fixture;
import com.technical.challenge.financialtransactions.BaseTest;
import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;
import com.technical.challenge.financialtransactions.service.impl.StatementTransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static com.technical.challenge.financialtransactions.fixture.StatementFixture.STATEMENT_FIXTURE_ONE;
import static com.technical.challenge.financialtransactions.fixture.StatementFixture.STATEMENT_FIXTURE_THREE;
import static com.technical.challenge.financialtransactions.fixture.TransactionFixture.TRANSACTION_FIXTURE_ONE;
import static com.technical.challenge.financialtransactions.fixture.TransactionFixture.TRANSACTION_FIXTURE_THREE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StatementTransactionServiceImplTest extends BaseTest {

    @Mock
    private StatementService statementService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private StatementTransactionServiceImpl statementTransactionService;

    @Test
    void getStatementWithTransaction_MustReturnStatement_WhenTransactionIdIsValid() {
        var transactionId = "3f5a45b2-135a-4660-aef1-0f0bbe5ea8df";

        Statement statement = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_ONE);
        Transaction transaction = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);

        when(statementService.findStatementByTransactionId(transactionId)).thenReturn(statement);
        when(transactionService.findByTransactionId(transactionId)).thenReturn(transaction);

        var result = statementTransactionService.getStatementWithTransaction(transactionId);

        assertEquals(transaction.getAmount(), result.getAmount());
        assertEquals(statement.getAmount(), result.getFinalAmount());
        assertEquals(statement.getStatus(), Status.PAID);
        assertEquals(statement.getPaymentDate(), result.getPaymentDate());

        verify(statementService, times(1)).findStatementByTransactionId(transactionId);
        verify(transactionService, times(1)).findByTransactionId(transactionId);
    }

    @Test
    void getAllStatementWithTransaction_MustReturnStatement_WhenRequestIsValid() {
        var transactionIdOne = "3f5a45b2-135a-4660-aef1-0f0bbe5ea8df";
        var transactionIdThree = "3f5a45b2-135a-4660-aef1-0f0bbe5ea8dx";

        LocalDate paymentDate = LocalDate.of(2024, 6, 18);
        Status status = Status.PAID;

        List<Transaction> transactionList = Fixture.from(Transaction.class).gimme(2, TRANSACTION_FIXTURE_ONE, TRANSACTION_FIXTURE_THREE);
        List<Statement> statementList = Fixture.from(Statement.class).gimme(2, STATEMENT_FIXTURE_ONE, STATEMENT_FIXTURE_THREE);

        transactionList.get(0).setTransactionId(transactionIdOne);
        transactionList.get(1).setTransactionId(transactionIdThree);
        statementList.get(0).setTransactionId(transactionIdOne);
        statementList.get(1).setTransactionId(transactionIdThree);

        when(statementService.findStatementsByCriteria(paymentDate, status)).thenReturn(statementList);
        when(transactionService.findByTransactionId(anyString())).thenReturn(transactionList.get(0), transactionList.get(1));

        List<StatementResponse> result = statementTransactionService.getAllStatementWithTransaction(paymentDate, status);

        assertEquals(2, result.size());
        assertEquals(transactionList.get(0).getAmount(), result.get(0).getAmount());
        assertEquals(statementList.get(0).getAmount(), result.get(0).getFinalAmount());
        assertEquals(statementList.get(0).getStatus(), result.get(0).getStatus());
        assertEquals(statementList.get(0).getPaymentDate(), result.get(0).getPaymentDate());

        assertEquals(transactionList.get(1).getAmount(), result.get(1).getAmount());
        assertEquals(statementList.get(1).getAmount(), result.get(1).getFinalAmount());
        assertEquals(statementList.get(1).getStatus(), result.get(1).getStatus());
        assertEquals(statementList.get(1).getPaymentDate(), result.get(1).getPaymentDate());

        verify(statementService, times(1)).findStatementsByCriteria(paymentDate, status);
        verify(transactionService, times(2)).findByTransactionId(anyString());
    }
}
