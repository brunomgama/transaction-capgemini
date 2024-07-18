package io.bgama.service;

import io.bgama.api.service.TransactionServiceAccess;
import io.bgama.datalayer.TransactionDataLayer;
import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TransactionService implements TransactionServiceAccess {

    @Inject
    TransactionDataLayer transactionDataLayer;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public TransactionResponse getTransactionDetails(Long transactionId) {
        return null;
    }
}
