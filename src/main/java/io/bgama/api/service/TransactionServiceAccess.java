package io.bgama.api.service;

import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;

public interface TransactionServiceAccess {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);
    TransactionResponse getTransactionDetails(Long transactionId);
}
