package io.bgama.service;

import io.bgama.api.service.AccountServiceAccess;
import io.bgama.datalayer.AccountDataLayer;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.datalayer.TransactionDataLayer;
import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import io.bgama.entity.Account;
import io.bgama.entity.Customer;
import io.bgama.entity.Transaction;
import io.bgama.error.ErrorMessage;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing account-related operations.
 * This class handles creation, retrieval, update, and deletion of accounts.
 * It interacts with AccountDataLayer and CustomerDataLayer for data persistence.
 * @author brunogama
 */
@ApplicationScoped
public class AccountService implements AccountServiceAccess {

    @Inject
    AccountDataLayer accountDataLayer;

    @Inject
    CustomerDataLayer customerDataLayer;

    @Inject
    TransactionDataLayer transactionDataLayer;

    /**
     * Creates a new account based on the provided account request.
     * @param accountRequest     The request containing account details.
     * @return                   The response containing details of the created account.
     * @throws NotFoundException If the associated customer is not found.
     */
    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest accountRequest) throws NotFoundException {
        checkCustomer(accountRequest);

        Account account = new Account();
        account.setCustomerId(accountRequest.getCustomerId());
        account.setBalance(accountRequest.getBalance());

        accountDataLayer.persist(account);

        if(accountRequest.getBalance() > 0) {
            Transaction transaction = new Transaction();
            transaction.setAccountId(account.getId());
            transaction.setDebit(true);
            transaction.setAmount(accountRequest.getBalance());

            transactionDataLayer.persist(transaction);
        }

        return new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance());
    }

    /**
     * Retrieves details of the account associated with the specified account ID.
     * @param accountId          The unique identifier of the account.
     * @return                   The response containing details of the account.
     * @throws NotFoundException If the account with the specified ID is not found.
     */
    @Override
    public AccountResponse getAccountDetails(Long accountId) throws NotFoundException {
        Account account = checkAccount(accountId);
        return new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance());
    }

    /**
     * Retrieves all accounts from the data layer and maps them to AccountResponse objects.
     * @return a list of AccountResponse objects containing account details.
     */
    @Override
    public List<AccountResponse> getAllAccounts() {
        List<Account> accountList = accountDataLayer.findAll(Sort.ascending("id")).list();
        return accountList.stream()
                .map(account -> new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves accounts from a specific user from the data layer and maps them to AccountResponse objects.
     * @return a list of AccountResponse objects containing account details.
     */
    @Override
    public List<AccountResponse> getAccountPerUser(Long userId) {
        List<Account> accountList = accountDataLayer.findAll(Sort.ascending("id")).list();
        return accountList.stream()
                .filter(account -> account.getCustomerId().equals(userId))
                .map(account -> new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance()))
                .collect(Collectors.toList());
    }

    /**
     * Updates details of the account associated with the specified account ID.
     * @param accountId          The unique identifier of the account.
     * @param accountRequest     The request containing updated account details.
     * @return                   The response containing details of the updated account.
     * @throws NotFoundException If the account with the specified ID is not found.
     */
    @Override
    @Transactional
    public AccountResponse updateAccountDetails(Long accountId, AccountRequest accountRequest) throws NotFoundException {
        Account account = checkAccount(accountId);
        checkCustomer(accountRequest);

        if (accountRequest.getCustomerId() != null) {
            account.setCustomerId(accountRequest.getCustomerId());
        }

        if (accountRequest.getBalance() != null) {
            account.setBalance(accountRequest.getBalance());
        }

        accountDataLayer.persist(account);

        return new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance());
    }

    /**
     * Deletes the account associated with the specified account ID.
     * @param accountId          The unique identifier of the account.
     * @throws NotFoundException If the account with the specified ID is not found.
     */
    @Override
    @Transactional
    public void deleteAccount(Long accountId) throws NotFoundException {
        Account account = checkAccount(accountId);
        accountDataLayer.delete(account);
    }

    /**
     * Checks if the account with the specified ID exists.
     * @param accountId          The unique identifier of the account to check.
     * @return                   The account entity if found.
     * @throws NotFoundException If the account with the specified ID is not found.
     */
    private Account checkAccount(Long accountId) throws NotFoundException {
        Account account = accountDataLayer.findById(accountId);
        if (account == null) {
            throw new NotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND.getMessage());
        }
        return account;
    }

    /**
     * Checks if the customer associated with the account request exists.
     * @param accountRequest     The request containing customer ID to check.
     * @throws NotFoundException If the customer with the specified ID is not found.
     */
    private void checkCustomer(AccountRequest accountRequest) throws NotFoundException {
        Customer customer = customerDataLayer.findById(accountRequest.getCustomerId());
        if (customer == null) {
            throw new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
        }
    }
}
