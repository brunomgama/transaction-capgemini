package io.bgama.datalayer;

import io.bgama.entity.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository class for managing {@link Transaction} entities.
 * Extends {@link PanacheRepository} for basic CRUD operations.
 * @author brunogama
 */
@ApplicationScoped
public class TransactionDataLayer implements PanacheRepository<Transaction> {

}
