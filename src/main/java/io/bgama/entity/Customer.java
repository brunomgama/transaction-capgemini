package io.bgama.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Example JPA entity.
 * To use it, get access to a JPA EntityManager via injection.
 */
@Entity
//@Table(name="baanana")
public class Customer {
    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String surname;
}
