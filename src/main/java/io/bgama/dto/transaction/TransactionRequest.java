package io.bgama.dto.transaction;

/**
 * Data transfer object representing a request to create or update a transaction.
 * @author brunogama
 */
public class TransactionRequest {
    private Long accountId;
    private Boolean isDebit;
    private Long amount;

    /**
     * Retrieves the ID of the account associated with the transaction.
     * @return The account ID.
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets the ID of the account associated with the transaction.
     * @param accountId The account ID to set.
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Retrieves whether the transaction is a debit or not.
     * @return {@code true} if the transaction is a debit, {@code false} otherwise.
     */
    public Boolean getIsDebit() {
        return isDebit;
    }

    /**
     * Sets whether the transaction is a debit or not.
     * @param isDebit {@code true} if the transaction is a debit, {@code false} otherwise.
     */
    public void setIsDebit(Boolean isDebit) {
        this.isDebit = isDebit;
    }

    /**
     * Retrieves the amount associated with the transaction.
     * @return The transaction amount.
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Sets the amount associated with the transaction.
     * @param amount The transaction amount to set.
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
