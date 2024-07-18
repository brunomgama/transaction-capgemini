package io.bgama.dto.transaction;

/**
 * Data transfer object representing a response containing transaction details.
 * @author brunogama
 */
public class TransactionResponse {
    private Long id;
    private Long accountId;
    private Boolean isDebit;
    private Long amount;

    /**
     * Constructs a TransactionResponse object with the specified details.
     * @param id        The unique identifier of the transaction.
     * @param accountId The ID of the account associated with the transaction.
     * @param isDebit   Whether the transaction is a debit or not.
     * @param amount    The amount associated with the transaction.
     */
    public TransactionResponse(Long id, Long accountId, Boolean isDebit, Long amount) {
        this.id = id;
        this.accountId = accountId;
        this.isDebit = isDebit;
        this.amount = amount;
    }

    /**
     * Retrieves the ID of the transaction.
     * @return The ID of the transaction.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the transaction.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

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
