package io.bgama.datalayer;

import io.bgama.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Repository class for managing {@link Customer} entities.
 * Extends {@link PanacheRepository} for basic CRUD operations.
 * @author brunogama
 */
@ApplicationScoped
public class CustomerDataLayer implements PanacheRepository<Customer> {

}
