package com.technical.challenge.financialtransactions.service;

import br.com.six2six.fixturefactory.Fixture;
import com.technical.challenge.financialtransactions.BaseTest;
import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.factory.PaymentStrategyFactory;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.StatementRepository;
import com.technical.challenge.financialtransactions.service.impl.StatementServiceImpl;
import com.technical.challenge.financialtransactions.strategy.PaymentStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;
import static com.technical.challenge.financialtransactions.fixture.StatementFixture.*;
import static com.technical.challenge.financialtransactions.fixture.TransactionFixture.TRANSACTION_FIXTURE_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StatementServiceImplTest extends BaseTest {

    @Mock
    private StatementRepository statementRepository;

    @Mock
    private PaymentStrategyFactory paymentStrategyFactory;

    @Mock
    private PaymentStrategy paymentStrategy;

    @InjectMocks
    private StatementServiceImpl statementService;

    @Test
    void createStatement_MustSaveStatement_WhenTransactionIsValid() {
        var transactionId = "4a981e85-615b-464b-a4ad-acbe8745243a";
        Transaction transaction = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);
        transaction.setTransactionId(transactionId);

        Statement statement = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_ONE);
        statement.setTransactionId(transaction.getTransactionId());

        when(paymentStrategyFactory.getStrategy(transaction.getPaymentMethod())).thenReturn(paymentStrategy);

        statementService.createStatement(transaction);

        verify(paymentStrategyFactory, times(1)).getStrategy(PaymentMethod.DEBIT);
        verify(paymentStrategyFactory, never()).getStrategy(PaymentMethod.CREDIT);
        verify(paymentStrategy, times(1)).processPayment(eq(transaction), any(Statement.class));
        verify(statementRepository, times(1)).save(any(Statement.class));
    }

    @Test
    void createStatement_MustThrowException_WhenAnErrorOccurred() {
        var transactionId = "4a981e85-615b-464b-a4ad-acbe8745243a";
        Transaction transaction = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);
        transaction.setTransactionId(transactionId);

        when(paymentStrategyFactory.getStrategy(transaction.getPaymentMethod())).thenReturn(paymentStrategy);
        doThrow(new RuntimeException()).when(statementRepository).save(any(Statement.class));

        var exception = assertThrows(ServiceException.class, () -> statementService.createStatement(transaction));

        assertEquals(UNKNOWN_ERROR, exception.getBaseException());
        verify(paymentStrategyFactory, times(1)).getStrategy(PaymentMethod.DEBIT);
        verify(paymentStrategy, times(1)).processPayment(eq(transaction), any(Statement.class));
        verify(statementRepository, times(1)).save(any(Statement.class));
    }

    @Test
    void findStatementByTransactionId_MustReturnStatement_WhenTransactionIdIsValid() {
        var transactionId = "4a981e85-615b-464b-a4ad-acbe8745243a";

        Statement statement = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_ONE);
        statement.setTransactionId(transactionId);

        when(statementRepository.findByTransactionId(transactionId)).thenReturn(Optional.of(statement));

        var result = statementService.findStatementByTransactionId(transactionId);

        assertEquals(statement, result);
        verify(statementRepository, times(1)).findByTransactionId(transactionId);
    }

    @Test
    void findStatementByTransactionId_MustThrowException_WhenTransactionIdIsInvalid() {
        var transactionId = "4a981e85-615b-464b-a4ad-acbe8745243a";

        when(statementRepository.findByTransactionId(transactionId)).thenReturn(Optional.empty());

        var exception = assertThrows(ServiceException.class, () -> statementService.findStatementByTransactionId(transactionId));

        assertEquals(TRANSACTION_NOT_FOUND, exception.getBaseException());
        verify(statementRepository, times(1)).findByTransactionId(transactionId);
    }

    @Test
    void findStatementsByCriteria_MustReturnStatements_WhenCriteriaIsValid() {
        Statement statementOne = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_ONE);
        Statement statementThree = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_THREE);

        var paymentDate = LocalDate.now();
        var status = Status.PAID;
        var statements = new ArrayList<>();
        statements.add(statementOne);
        statements.add(statementThree);

        when(statementRepository.findStatementsByCriteria(paymentDate, status)).thenReturn(List.of(statementOne, statementThree));

        List<Statement> result = statementService.findStatementsByCriteria(paymentDate, status);

        assertEquals(statements, result);
        verify(statementRepository, times(1)).findStatementsByCriteria(paymentDate, status);
    }

    @Test
    void findStatementsByCriteria_MustReturnEmptyList_WhenStatementNotFound() {

        var paymentDate = LocalDate.now();
        var status = Status.PAID;
        var statements = new ArrayList<>();

        when(statementRepository.findStatementsByCriteria(paymentDate, status)).thenReturn(List.of());

        List<Statement> result = statementService.findStatementsByCriteria(paymentDate, status);

        assertEquals(statements, result);
        verify(statementRepository, times(1)).findStatementsByCriteria(paymentDate, status);
    }

    @Test
    void updateStatusByCriteria_MustUpdateStatus_WhenCriteriaIsValid() {
        Statement statementTwo = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_TWO);
        Statement statementFour = Fixture.from(Statement.class).gimme(STATEMENT_FIXTURE_FOUR);

        var paymentDate = LocalDate.now();
        var status = Status.WAITING_FUNDS;

        when(statementRepository.findStatementsByCriteria(paymentDate, status)).thenReturn(List.of(statementTwo, statementFour));

        assertEquals(Status.WAITING_FUNDS, statementTwo.getStatus());
        assertEquals(Status.WAITING_FUNDS, statementFour.getStatus());

        statementService.updateStatusByCriteria(paymentDate, status);

        assertEquals(Status.PAID, statementTwo.getStatus());
        assertEquals(Status.PAID, statementFour.getStatus());
        verify(statementRepository, times(2)).save(any(Statement.class));
    }
}
