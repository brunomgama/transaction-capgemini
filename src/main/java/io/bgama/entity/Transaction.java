package io.bgama.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Example JPA entity.
 * To use it, get access to a JPA EntityManager via injection.
 */
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Long accountId;

    private Boolean isDebit;

    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getDebit() {
        return isDebit;
    }

    public void setDebit(Boolean debit) {
        isDebit = debit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
