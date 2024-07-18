package io.bgama.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * JPA entity representing an account.
 * To use it, get access to a JPA EntityManager via injection.
 * @author brunogama
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;

    private Long balance;

    /**
     * Retrieves the ID of the account.
     * @return The ID of the account.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the account.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the customer associated to the account.
     * @return The ID of the customer.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer into the account.
     * @param customerId The ID to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the balance of the account.
     * @return The balance of the account.
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
