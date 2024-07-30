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

    private String bankName;

    private String iban;

    private Double balance;

    private Long customerId;

    private String customerName;

    private String iconPath;

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
     * Retrieves the bank name of the account.
     * @return The bank name of the account.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the bank name of the account.
     * @param bankName The bank name to set.
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Retrieves the balance of the account.
     * @return The balance of the account.
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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
