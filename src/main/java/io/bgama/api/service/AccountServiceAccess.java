package io.bgama.api.service;

import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

/**
 * Interface for accessing account-related operations.
 * @author brunogama
 */
public interface AccountServiceAccess {

    /**
     * Creates a new account based on the provided account request.
     *
     * @param accountRequest        The request containing account details.
     * @return                      The response containing details of the created account.
     * @throws NotFoundException    If the associated resource (e.g., customer) is not found.
     */
    AccountResponse createAccount(AccountRequest accountRequest) throws NotFoundException;

    /**
     * Retrieves details of the account associated with the specified account ID.
     *
     * @param accountId             The unique identifier of the account.
     * @return                      The response containing details of the account.
     * @throws NotFoundException    If the account with the specified ID is not found.
     */
    AccountResponse getAccountDetails(Long accountId) throws NotFoundException;

    /**
     * Retrieves a list of all customers.
     * @return A list of CustomerResponse objects.
     */
    List<AccountResponse> getAllAccounts();

    /**
     * Updates details of the account associated with the specified account ID.
     *
     * @param accountId             The unique identifier of the account.
     * @param accountRequest        The request containing updated account details.
     * @return                      The response containing details of the updated account.
     * @throws NotFoundException    If the account with the specified ID is not found.
     */
    AccountResponse updateAccountDetails(Long accountId, AccountRequest accountRequest) throws NotFoundException;

    /**
     * Deletes the account associated with the specified account ID.
     *
     * @param accountId             The unique identifier of the account.
     * @throws NotFoundException    If the account with the specified ID is not found.
     */
    void deleteAccount(Long accountId) throws NotFoundException;
}
