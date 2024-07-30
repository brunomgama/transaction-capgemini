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

    private String destination;

    private Long accountId;

    private Long transaction_type;

    private String transaction_type_name;

    private Long transaction_category;

    private String transaction_category_name;

    private Boolean state;

    private Boolean isDebit;

    private Double amount;

    private Boolean repetition;


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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Long getTransactionType() {
        return transaction_type;
    }

    public void setTransactionType(Long transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransactionTypeName() {
        return transaction_type_name;
    }

    public void setTransactionTypeName(String transaction_type_name) {
        this.transaction_type_name = transaction_type_name;
    }

    public Long getTransactionCategory() {
        return transaction_category;
    }

    public void setTransactionCategory(Long transaction_category) {
        this.transaction_category = transaction_category;
    }

    public String getTransactionCategoryName() {
        return transaction_category_name;
    }

    public void setTransactionCategoryName(String transaction_category_name) {
        this.transaction_category_name = transaction_category_name;
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
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount associated with the transaction.
     * @param amount The amount to set.
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
