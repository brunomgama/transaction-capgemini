package io.bgama.dto.transaction;

/**
 * Data transfer object representing a request to create or update a transaction.
 * @author brunogama
 */
public class TransactionRequest {
    private String destination;
    private Long accountId;
    private Long transaction_type;
    private Long transaction_category;
    private Boolean state;
    private Boolean isDebit;
    private Double amount;
    private Boolean repetition;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Long getTransactionType() {
        return transaction_type;
    }

    public void setTransactionType(Long transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Long getTransactionCategory() {
        return transaction_category;
    }

    public void setTransactionCategory(Long transaction_category) {
        this.transaction_category = transaction_category;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
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
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount associated with the transaction.
     * @param amount The transaction amount to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getRepetition() {
        return repetition;
    }

    public void setRepetition(Boolean repetition) {
        this.repetition = repetition;
    }
}
