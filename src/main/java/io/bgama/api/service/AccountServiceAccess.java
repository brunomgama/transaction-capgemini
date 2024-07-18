package io.bgama.api.service;

import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;

public interface AccountServiceAccess {

    AccountResponse createAccount(AccountRequest accountRequest);
    AccountResponse getAccountDetails(Long accountId);
}
