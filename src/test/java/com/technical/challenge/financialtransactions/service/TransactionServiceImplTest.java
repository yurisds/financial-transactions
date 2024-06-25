package com.technical.challenge.financialtransactions.service;

import br.com.six2six.fixturefactory.Fixture;
import com.technical.challenge.financialtransactions.BaseTest;
import com.technical.challenge.financialtransactions.exception.ServiceException;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.repository.TransactionRepository;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import com.technical.challenge.financialtransactions.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static com.technical.challenge.financialtransactions.exception.BaseException.TRANSACTION_NOT_FOUND;
import static com.technical.challenge.financialtransactions.exception.BaseException.UNKNOWN_ERROR;
import static com.technical.challenge.financialtransactions.fixture.TransactionFixture.*;
import static com.technical.challenge.financialtransactions.fixture.TransactionRequestFixture.TRANSACTION_REQUEST_FIXTURE_ONE;
import static com.technical.challenge.financialtransactions.fixture.TransactionRequestFixture.TRANSACTION_REQUEST_FIXTURE_TWO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest extends BaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatementService statementService;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void findAll_MustReturnTransactions_WhenTransactionsExist() {
        Transaction transactionOne = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);
        Transaction transactionTwo = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_TWO);
        Transaction transactionThree = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_THREE);
        Transaction transactionFour = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_FOUR);

        when(transactionRepository.findAll()).thenReturn(List.of(transactionOne, transactionTwo, transactionThree, transactionFour));

        var result = transactionService.findAll();

        assertNotNull(result);
        assertEquals(4, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void findAll_MustThrowException_WhenAnErrorOccurred() {
        when(transactionRepository.findAll()).thenThrow(new RuntimeException());

        ServiceException exception = assertThrows(ServiceException.class, () -> transactionService.findAll());

        assertEquals(UNKNOWN_ERROR, exception.getBaseException());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void findTransactionsByCriteria_MustReturnTransactions_WhenCriteriaMatch() {
        Transaction transaction = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);

        when(transactionRepository.findTransactionsByCriteria(any(), any(), any())).thenReturn(List.of(transaction));

        var result = transactionService.findTransactionsByCriteria("Test Transaction", PaymentMethod.CREDIT, "John Doe");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionRepository, times(1)).findTransactionsByCriteria(any(), any(), any());
    }

    @Test
    void findTransactionsByCriteria_MustThrowException_WhenAnErrorOccurred() {
        when(transactionRepository.findTransactionsByCriteria(any(), any(), any())).thenThrow(new RuntimeException());

        var exception = assertThrows(ServiceException.class, () -> transactionService.findTransactionsByCriteria("Test Transaction", PaymentMethod.CREDIT, "John Doe"));

        assertEquals(UNKNOWN_ERROR, exception.getBaseException());
        verify(transactionRepository, times(1)).findTransactionsByCriteria(any(), any(), any());
    }

    @Test
    void findByTransactionId_MustReturnTransaction_WhenTransactionIdIsValid() {
        var transactionId = "4a981e85-615b-464b-a4ad-acbe8745243a";
        Transaction transaction = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);

        when(transactionRepository.findByTransactionId(transactionId)).thenReturn(Optional.of(transaction));

        transaction.setTransactionId(transactionId);

        var result = transactionService.findByTransactionId(transactionId);

        assertNotNull(result);
        assertEquals(transaction.getTransactionId(), result.getTransactionId());
        verify(transactionRepository, times(1)).findByTransactionId(anyString());
    }

    @Test
    void findByTransactionId_MustThrowException_WhenTransactionIdIsInvalid() {
        when(transactionRepository.findByTransactionId(anyString())).thenReturn(Optional.empty());

        var exception = assertThrows(ServiceException.class, () -> transactionService.findByTransactionId("id_id_id"));

        assertEquals(TRANSACTION_NOT_FOUND, exception.getBaseException());
        verify(transactionRepository, times(1)).findByTransactionId(anyString());
    }

    @Test
    void findByTransactionId_MustThrowException_WhenAnErrorOccurred() {
        when(transactionRepository.findByTransactionId(anyString())).thenThrow(new RuntimeException());

        var exception = assertThrows(ServiceException.class, () -> transactionService.findByTransactionId("id_id_id"));

        assertEquals(UNKNOWN_ERROR, exception.getBaseException());
        verify(transactionRepository, times(1)).findByTransactionId(anyString());
    }

    @Test
    void createTransaction_MustCreateTransaction_WhenRequestIsValid() {
        var transactionId = "4a981e85-615b-464b-a4ad-acbe8745243a";
        Transaction transaction = Fixture.from(Transaction.class).gimme(TRANSACTION_FIXTURE_ONE);

        transaction.setTransactionId(transactionId);
        TransactionRequest transactionRequest = Fixture.from(TransactionRequest.class).gimme(TRANSACTION_REQUEST_FIXTURE_ONE);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        var result = transactionService.createTransaction(transactionRequest);

        assertNotNull(result);
        assertEquals(transaction.getTransactionId(), result.getTransactionId());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(statementService, times(1)).createStatement(any(Transaction.class));
    }

    @Test
    void createTransaction_MustThrowException_WhenAnErrorOccurred() {
        TransactionRequest transactionRequest = Fixture.from(TransactionRequest.class).gimme(TRANSACTION_REQUEST_FIXTURE_TWO);

        when(transactionRepository.save(any())).thenThrow(new RuntimeException());

        var exception = assertThrows(ServiceException.class, () -> transactionService.createTransaction(transactionRequest));

        assertEquals(UNKNOWN_ERROR, exception.getBaseException());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(statementService, never()).createStatement(any(Transaction.class));
    }
}