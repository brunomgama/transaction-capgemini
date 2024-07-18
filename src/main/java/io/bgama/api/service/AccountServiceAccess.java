package io.bgama.api.service;

import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import jakarta.ws.rs.NotFoundException;

public interface AccountServiceAccess {

    AccountResponse createAccount(AccountRequest accountRequest) throws NotFoundException;

    AccountResponse getAccountDetails(Long accountId) throws NotFoundException;

    AccountResponse updateAccountDetails(Long accountId, AccountRequest accountRequest) throws NotFoundException;

    void deleteAccount(Long accountId) throws NotFoundException;
}
