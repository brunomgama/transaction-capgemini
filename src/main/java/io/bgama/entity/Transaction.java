package io.bgama.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * JPA entity representing a transaction.
 * To use it, get access to a JPA EntityManager via injection.
 * @author brunogama
 */
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Long accountId;

    private Boolean isDebit;

    private Long amount;

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
     * @return The account ID associated with the transaction.
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
    public Boolean getDebit() {
        return isDebit;
    }

    /**
     * Sets whether the transaction is a debit or not.
     * @param isDebit {@code true} if the transaction is a debit, {@code false} otherwise.
     */
    public void setDebit(Boolean isDebit) {
        this.isDebit = isDebit;
    }

    /**
     * Retrieves the amount associated with the transaction.
     * @return The amount associated with the transaction.
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Sets the amount associated with the transaction.
     * @param amount The amount to set.
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
