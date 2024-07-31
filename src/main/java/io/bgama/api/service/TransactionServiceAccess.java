package io.bgama.api.service;

import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

/**
 * Interface for accessing transaction-related operations.
 * @author brunogama
 */
public interface TransactionServiceAccess {

    /**
     * Creates a new transaction based on the provided transaction request.
     *
     * @param transactionRequest    The request containing transaction details.
     * @return                      The response containing details of the created transaction.
     * @throws NotFoundException    If the associated resource (e.g., account) is not found.
     */
    TransactionResponse createTransaction(TransactionRequest transactionRequest) throws NotFoundException;

    /**
     * Retrieves details of the transaction associated with the specified transaction ID.
     *
     * @param transactionId         The unique identifier of the transaction.
     * @return                      The response containing details of the transaction.
     * @throws NotFoundException    If the transaction with the specified ID is not found.
     */
    TransactionResponse getTransactionDetails(Long transactionId) throws NotFoundException;

    /**
     * Retrieves a list of all customers.
     * @return A list of CustomerResponse objects.
     */
    List<TransactionResponse> getAllTransactions();


    List<TransactionResponse> getFutureTransactions();
    /**
     * Retrieves details of the transaction associated with the specified account ID.
     *
     * @param accountId                The unique identifier of the account.
     * @return                      The response containing details of the account.
     * @throws NotFoundException    If the account with the specified ID is not found.
     */
    List<TransactionResponse> getTransactionPerAccount(Long accountId) throws NotFoundException;

    /**
     * Updates details of the transaction associated with the specified transaction ID.
     *
     * @param transactionId         The unique identifier of the transaction.
     * @param transactionRequest    The request containing updated transaction details.
     * @return                      The response containing details of the updated transaction.
     * @throws NotFoundException    If the transaction with the specified ID is not found.
     */
    TransactionResponse updateTransactionDetails(Long transactionId, TransactionRequest transactionRequest) throws NotFoundException;

    /**
     * Deletes the transaction associated with the specified transaction ID.
     *
     * @param transactionId         The unique identifier of the transaction.
     * @throws NotFoundException    If the transaction with the specified ID is not found.
     */
    void deleteTransaction(Long transactionId) throws NotFoundException;
}
