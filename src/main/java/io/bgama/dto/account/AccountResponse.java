package io.bgama.dto.account;

/**
 * Data transfer object representing a response containing account details.
 * @author brunogama
 */
public class AccountResponse {
    private Long accountId;
    private Long customerId;
    private Double balance;

    /**
     * Constructs an AccountResponse object with the specified details.
     *
     * @param accountId  The unique identifier of the account.
     * @param customerId The customer ID associated with the account.
     * @param balance    The balance of the account.
     */
    public AccountResponse(Long accountId, Long customerId, Double balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
    }

    /**
     * Retrieves the account ID.
     * @return The account ID.
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID.
     * @param accountId The account ID to set.
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Retrieves the customer ID associated with the account.
     * @return The customer ID.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID associated with the account.
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the balance of the account.
     * @return The balance.
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     * @param balance The balance to set.
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
