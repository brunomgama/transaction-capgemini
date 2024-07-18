package io.bgama.api.service;

import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import jakarta.ws.rs.NotFoundException;

public interface TransactionServiceAccess {
    
    TransactionResponse createTransaction(TransactionRequest transactionRequest) throws NotFoundException;

    TransactionResponse getTransactionDetails(Long transactionId) throws NotFoundException;

    TransactionResponse updateTransactionDetails(Long transactionId, TransactionRequest transactionRequest) throws NotFoundException;

    void deleteTransaction(Long transactionId) throws NotFoundException;
}
