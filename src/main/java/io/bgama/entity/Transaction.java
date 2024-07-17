package io.bgama.entity;

import jakarta.persistence.*;

/**
 * Example JPA entity.
 * To use it, get access to a JPA EntityManager via injection.
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    public Long id;

    @OneToOne
    public Account account;

    public Boolean isDebit;

    public Long amount;
}
