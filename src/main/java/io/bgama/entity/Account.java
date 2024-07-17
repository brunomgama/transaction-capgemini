package io.bgama.entity;

import jakarta.persistence.*;

/**
 * Example JPA entity.
 * To use it, get access to a JPA EntityManager via injection.
 */
@Entity
public class Account {
    @Id
    @GeneratedValue
    public Long id;

    @OneToOne
    public Customer customer;

    public Long balance;
}
