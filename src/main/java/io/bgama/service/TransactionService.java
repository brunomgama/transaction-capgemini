package io.bgama.service;

import io.bgama.api.service.TransactionServiceAccess;
import io.bgama.datalayer.AccountDataLayer;
import io.bgama.datalayer.TransactionDataLayer;
import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import io.bgama.entity.Account;
import io.bgama.entity.Transaction;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling transaction-related operations.
 * @author brunogama
 */
@ApplicationScoped
public class TransactionService implements TransactionServiceAccess {

    @Inject
    TransactionDataLayer transactionDataLayer;
    @Inject
    AccountDataLayer accountDataLayer;

    /**
     * Creates a new transaction.
     * @param transactionRequest the request object containing transaction details
     * @return the response object containing created transaction details
     * @throws NotFoundException if the associated account is not found
     */
    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) throws NotFoundException {
        checkAccount(transactionRequest);

        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionRequest.getAccountId());
        transaction.setDebit(transactionRequest.getIsDebit());
        transaction.setAmount(transactionRequest.getAmount());

        transactionDataLayer.persist(transaction);
        return new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount());
    }

    /**
     * Retrieves the details of a specific transaction.
     * @param transactionId the ID of the transaction to retrieve
     * @return the response object containing transaction details
     * @throws NotFoundException if the transaction is not found
     */
    @Override
    public TransactionResponse getTransactionDetails(Long transactionId) throws NotFoundException {
        Transaction transaction = checkTransaction(transactionId);

        return new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount());
    }

    /**
     * Retrieves all transactions.
     * @return a list of response objects containing transaction details
     */
    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionDataLayer.findAll(Sort.ascending("id")).list();
        return transactions.stream()
                .map(transaction -> new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves transactions from a specific account from the data layer and maps them to TransactionResponse objects.
     * @return a list of TransactionResponse objects containing account details.
     */
    @Override
    public List<TransactionResponse> getTransactionPerAccount(Long accountId) {
        List<Transaction> transactionList = transactionDataLayer.find("accountId", Sort.ascending("id"), accountId).list();
        return transactionList.stream()
                .map(transaction -> new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount()))
                .collect(Collectors.toList());
    }

    /**
     * Updates the details of an existing transaction.
     * @param transactionId      the ID of the transaction to update
     * @param transactionRequest the request object containing updated transaction details
     * @return the response object containing updated transaction details
     * @throws NotFoundException if the transaction or associated account is not found
     */
    @Override
    @Transactional
    public TransactionResponse updateTransactionDetails(Long transactionId, TransactionRequest transactionRequest) throws NotFoundException {
        Transaction transaction = checkTransaction(transactionId);

        checkAccount(transactionRequest);

        transaction.setAccountId(transactionRequest.getAccountId());
        transaction.setDebit(transactionRequest.getIsDebit());
        transaction.setAmount(transactionRequest.getAmount());

        transactionDataLayer.persist(transaction);
        return new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount());
    }

    /**
     * Deletes a specific transaction.
     * @param transactionId the ID of the transaction to delete
     * @throws NotFoundException if the transaction is not found
     */
    @Override
    @Transactional
    public void deleteTransaction(Long transactionId) throws NotFoundException {
        Transaction transaction = checkTransaction(transactionId);
        transactionDataLayer.delete(transaction);
    }

    /**
     * Checks if a transaction exists for the given ID.
     * @param transactionId the ID of the transaction to check
     * @return the found transaction
     * @throws NotFoundException if the transaction is not found
     */
    private Transaction checkTransaction(Long transactionId) {
        Transaction transaction = transactionDataLayer.findById(transactionId);
        if (transaction == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        return transaction;
    }

    /**
     * Checks if an account exists for the given transaction request.
     * @param transactionRequest the request object containing transaction details
     * @throws NotFoundException if the account is not found
     */
    private void checkAccount(TransactionRequest transactionRequest) {
        Account account = accountDataLayer.findById(transactionRequest.getAccountId());
        if (account == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
    }
}
