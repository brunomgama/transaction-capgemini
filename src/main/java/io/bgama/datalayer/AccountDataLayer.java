package io.bgama.datalayer;

import io.bgama.entity.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository class for managing {@link Account} entities.
 * Extends {@link PanacheRepository} for basic CRUD operations.
 * @author brunogama
 */
@ApplicationScoped
public class AccountDataLayer implements PanacheRepository<Account> {

}
