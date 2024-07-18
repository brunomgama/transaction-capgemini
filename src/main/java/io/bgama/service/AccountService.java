package io.bgama.service;

import io.bgama.api.service.AccountServiceAccess;
import io.bgama.datalayer.AccountDataLayer;
import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountService implements AccountServiceAccess {

    @Inject
    AccountDataLayer accountDataLayer;

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public AccountResponse getAccountDetails(Long accountId) {
        return null;
    }
}
