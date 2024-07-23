package io.bgama.service;

import io.bgama.datalayer.AccountDataLayer;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.datalayer.TransactionDataLayer;
import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import io.bgama.entity.Account;
import io.bgama.entity.Customer;
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
public class AccountServiceTest {
    @Inject
    AccountService accountService;
    @InjectMock
    @MockitoConfig
    AccountDataLayer accountDataLayer;
    @InjectMock
    @MockitoConfig
    CustomerDataLayer customerDataLayer;
    @InjectMock
    @MockitoConfig
    TransactionDataLayer transactionDataLayer;


    @Test
    void testCreateAccount() {
        Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getId()).thenReturn(1L);

        Mockito.when(customerDataLayer.findById(Mockito.anyLong())).thenReturn(new Customer());

        AccountRequest request = new AccountRequest();
        request.setBalance(Double.valueOf(100.0));
        request.setCustomerId(1L);

        accountService.createAccount(request);
        Mockito.verify(accountDataLayer, Mockito.times(1)).persist(Mockito.any(Account.class));
        Mockito.verify(transactionDataLayer, Mockito.times(1)).persist(Mockito.any(Transaction.class));
    }

    @Test
    void testCreateAccount_noBalance() {
        Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getId()).thenReturn(1L);

        Mockito.when(customerDataLayer.findById(Mockito.anyLong())).thenReturn(new Customer());

        AccountRequest request = new AccountRequest();
        request.setCustomerId(1L);

        accountService.createAccount(request);
        Mockito.verify(accountDataLayer, Mockito.times(1)).persist(Mockito.any(Account.class));
        Mockito.verify(transactionDataLayer, Mockito.times(0)).persist(Mockito.any(Transaction.class));
    }

    @Test
    void testCreateAccount_noCustomer() {
        AccountRequest request = new AccountRequest();
        request.setBalance(Double.valueOf(100.0));
        request.setCustomerId(1L);

        Assertions.assertThrows(NotFoundException.class, () -> accountService.createAccount(request));
    }

    @Test
    void testGetAccountDetails() {
        Account resultAccount = new Account();
        resultAccount.setId(1L);
        resultAccount.setBalance(Double.valueOf(100.0));
        resultAccount.setCustomerId(1L);

        Mockito.when(accountDataLayer.findById(Mockito.anyLong())).thenReturn(resultAccount);

        AccountResponse account = accountService.getAccountDetails(1L);
        Assertions.assertEquals(resultAccount.getId(), account.getAccountId());
        Assertions.assertEquals(resultAccount.getBalance(), account.getBalance());
        Assertions.assertEquals(resultAccount.getCustomerId(), account.getCustomerId());
    }

    @Test
    void testGetAccountDetails_notFound() {
        Assertions.assertThrows(NotFoundException.class, () -> accountService.getAccountDetails(2L));
    }

    @Test
    void testGetAllAccounts() {
        Account resultAccount = new Account();
        resultAccount.setId(1L);
        resultAccount.setBalance(Double.valueOf(100.0));
        resultAccount.setCustomerId(1L);

        PanacheQuery<Account> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(mockQuery.list()).thenReturn(List.of(resultAccount));
        Mockito.when(accountDataLayer.findAll(Mockito.any(Sort.class))).thenReturn(mockQuery);

        List<AccountResponse> response = accountService.getAllAccounts();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.size());
    }

    @Test
    void testGetAccountPerUser() {
        Account resultAccount = new Account();
        resultAccount.setId(1L);
        resultAccount.setBalance(Double.valueOf(100.0));
        resultAccount.setCustomerId(1L);

        PanacheQuery<Account> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(mockQuery.list()).thenReturn(List.of(resultAccount));
        Mockito.when(accountDataLayer.find(Mockito.anyString(), Mockito.any(Sort.class), Mockito.any(Object[].class))).thenReturn(mockQuery);

        List<AccountResponse> response = accountService.getAccountPerUser(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.size());
    }

    @Test
    void testUpdateAccountDetails() {
        Customer mockCustomer = Mockito.mock(Customer.class);
        Mockito.when(mockCustomer.getId()).thenReturn(1L);

        Mockito.when(customerDataLayer.findById(Mockito.anyLong())).thenReturn(new Customer());

        Account resultAccount = new Account();
        resultAccount.setId(1L);
        resultAccount.setBalance(Double.valueOf(100.0));
        resultAccount.setCustomerId(1L);

        Mockito.when(accountDataLayer.findById(Mockito.anyLong())).thenReturn(resultAccount);

        AccountRequest request = new AccountRequest();
        request.setCustomerId(2L);
        request.setBalance(Double.valueOf(1000.0));

        AccountResponse response = accountService.updateAccountDetails(1L, request);

        Mockito.verify(accountDataLayer, Mockito.times(1)).persist(Mockito.any(Account.class));
        Assertions.assertEquals(resultAccount.getId(), response.getAccountId());
        Assertions.assertEquals(2, response.getCustomerId());
        Assertions.assertEquals(Double.valueOf(1000.0), response.getBalance());
    }

    @Test
    void testDeleteAccount() {
        Account resultAccount = new Account();
        resultAccount.setId(1L);
        resultAccount.setBalance(Double.valueOf(100.0));
        resultAccount.setCustomerId(1L);

        Mockito.when(accountDataLayer.findById(Mockito.anyLong())).thenReturn(resultAccount);

        accountService.deleteAccount(1L);
        Mockito.verify(accountDataLayer, Mockito.times(1)).delete(Mockito.any(Account.class));
    }
}
