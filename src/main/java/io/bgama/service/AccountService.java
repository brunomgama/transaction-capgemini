package io.bgama.service;

import io.bgama.api.service.AccountServiceAccess;
import io.bgama.datalayer.AccountDataLayer;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import io.bgama.entity.Account;
import io.bgama.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AccountService implements AccountServiceAccess {

    @Inject
    AccountDataLayer accountDataLayer;
    @Inject
    CustomerDataLayer customerDataLayer;

    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest accountRequest) throws NotFoundException {
        Customer customer = customerDataLayer.findById(accountRequest.getCustomerId());
        if (customer == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        Account account = new Account();
        account.setCustomerId(accountRequest.getCustomerId());
        account.setBalance(accountRequest.getBalance());

        accountDataLayer.persist(account);

        return new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance());
    }


    @Override
    public AccountResponse getAccountDetails(Long accountId) throws NotFoundException {
        Account account = accountDataLayer.findById(accountId);
        if (account == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        return new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance());
    }

    @Override
    @Transactional
    public AccountResponse updateAccountDetails(Long accountId, AccountRequest accountRequest) throws NotFoundException {
        Account account = accountDataLayer.findById(accountId);
        if (account == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        Customer customer = customerDataLayer.findById(accountRequest.getCustomerId());
        if (customer == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        account.setCustomerId(accountRequest.getCustomerId());
        account.setBalance(accountRequest.getBalance());
        accountDataLayer.persist(account);

        return new AccountResponse(account.getId(), account.getCustomerId(), account.getBalance());
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) throws NotFoundException {
        Account account = accountDataLayer.findById(accountId);
        if (account == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        accountDataLayer.delete(account);
    }
}
