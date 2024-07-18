package io.bgama.service;

import io.bgama.api.service.TransactionServiceAccess;
import io.bgama.datalayer.AccountDataLayer;
import io.bgama.datalayer.TransactionDataLayer;
import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import io.bgama.entity.Account;
import io.bgama.entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class TransactionService implements TransactionServiceAccess {

    @Inject
    TransactionDataLayer transactionDataLayer;
    @Inject
    AccountDataLayer accountDataLayer;

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) throws NotFoundException {
        Account account = accountDataLayer.findById(transactionRequest.getAccountId());
        if (account == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionRequest.getAccountId());
        transaction.setDebit(transactionRequest.getIsDebit());
        transaction.setAmount(transactionRequest.getAmount());

        transactionDataLayer.persist(transaction);
        return new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount());
    }

    @Override
    public TransactionResponse getTransactionDetails(Long transactionId) throws NotFoundException {
        Transaction transaction = transactionDataLayer.findById(transactionId);
        if (transaction == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        return new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount());
    }

    @Override
    @Transactional
    public TransactionResponse updateTransactionDetails(Long transactionId, TransactionRequest transactionRequest) throws NotFoundException {
        Transaction transaction = transactionDataLayer.findById(transactionId);
        if (transaction == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        Account account = accountDataLayer.findById(transactionRequest.getAccountId());
        if (account == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }

        transaction.setAccountId(transactionRequest.getAccountId());
        transaction.setDebit(transactionRequest.getIsDebit());
        transaction.setAmount(transactionRequest.getAmount());

        transactionDataLayer.persist(transaction);
        return new TransactionResponse(transaction.getId(), transaction.getAccountId(), transaction.getDebit(), transaction.getAmount());
    }

    @Override
    @Transactional
    public void deleteTransaction(Long transactionId) throws NotFoundException {
        Transaction transaction = transactionDataLayer.findById(transactionId);
        if (transaction == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        transactionDataLayer.delete(transaction);
    }
}
