package io.bgama.service;

import io.bgama.datalayer.TransactionDataLayer;
import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import io.bgama.entity.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
@TestTransaction
public class TransactionServiceTest {
    @Inject
    TransactionService transactionService;
    @InjectMock
    @MockitoConfig
    TransactionDataLayer transactionDataLayer;

    @Test
    void testCreateTransaction() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(2L);
        request.setIsDebit(true);
        request.setAmount(Double.valueOf(200.23));

        transactionService.createTransaction(request);
        Mockito.verify(transactionDataLayer, Mockito.times(1)).persist(Mockito.any(Transaction.class));
    }

    @Test
    void testGetTransactionDetails() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(2L);
        transaction.setDebit(true);
        transaction.setAmount(Double.valueOf(200.23));

        Mockito.when(transactionDataLayer.findById(Mockito.anyLong())).thenReturn(transaction);

        TransactionResponse response = transactionService.getTransactionDetails(2L);
        Assertions.assertEquals(transaction.getId(), response.getId());
        Assertions.assertEquals(transaction.getAccountId(), response.getAccountId());
        Assertions.assertEquals(transaction.getDebit(), response.getIsDebit());
        Assertions.assertEquals(transaction.getAmount(), response.getAmount());
    }

    @Test
    void testGetAllCustomers() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(2L);
        transaction.setDebit(true);
        transaction.setAmount(Double.valueOf(200.23));

        PanacheQuery<Transaction> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(mockQuery.list()).thenReturn(List.of(transaction));
        Mockito.when(transactionDataLayer.findAll(Mockito.any(Sort.class))).thenReturn(mockQuery);

        List<TransactionResponse> response = transactionService.getAllTransactions();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.size());
    }

    @Test
    void testUpdateTransactionDetails() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(2L);
        transaction.setDebit(true);
        transaction.setAmount(Double.valueOf(200.23));

        Mockito.when(transactionDataLayer.findById(Mockito.anyLong())).thenReturn(transaction);

        TransactionRequest request = new TransactionRequest();
        request.setIsDebit(false);
        request.setAmount(Double.valueOf(100.92));
        request.setAccountId(3L);

        TransactionResponse response = transactionService.updateTransactionDetails(2L, request);
        Mockito.verify(transactionDataLayer, Mockito.times(1)).persist(Mockito.any(Transaction.class));

        Assertions.assertEquals(transaction.getAccountId(), response.getAccountId());
        Assertions.assertEquals(transaction.getAmount(), response.getAmount());
        Assertions.assertEquals(transaction.getDebit(), response.getIsDebit());
    }

    @Test
    void testGetTransactionPerAccount() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(2L);
        transaction.setDebit(true);
        transaction.setAmount(Double.valueOf(200.23));
        transaction.setId(1L);

        PanacheQuery<Transaction> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(mockQuery.list()).thenReturn(List.of(transaction));
        Mockito.when(transactionDataLayer.find(Mockito.anyString(), Mockito.any(Sort.class), Mockito.any(Parameters.class))).thenReturn(mockQuery);

        List<TransactionResponse> response = transactionService.getTransactionPerAccount(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.size());
    }

    @Test
    void testDeleteTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(2L);
        transaction.setDebit(true);
        transaction.setAmount(Double.valueOf(200.23));

        Mockito.when(transactionDataLayer.findById(Mockito.anyLong())).thenReturn(transaction);

        transactionService.deleteTransaction(2L);
        Mockito.verify(transactionDataLayer, Mockito.times(1)).delete(Mockito.any(Transaction.class));
    }

    @Test
    void testGetTransactionDetails_notFound() {
        Assertions.assertThrows(NotFoundException.class, () -> transactionService.getTransactionDetails(30L));
    }

    @Test
    void testCheckAccount_notFound() {
        TransactionRequest request = new TransactionRequest();
        request.setIsDebit(false);
        request.setAmount(Double.valueOf(100.92));
        request.setAccountId(10L);

        Assertions.assertThrows(NotFoundException.class, () -> transactionService.createTransaction(request));
    }
}
