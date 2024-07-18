package io.bgama.dto.account;

/**
 * Data transfer object representing a request to create or update an account.
 * @author brunogama
 */
public class AccountRequest {
    private Long customerId;
    private Long balance;

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
    public Long getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account.
     * @param balance The balance to set.
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
