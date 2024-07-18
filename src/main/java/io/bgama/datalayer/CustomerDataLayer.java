package io.bgama.datalayer;

import io.bgama.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerDataLayer implements PanacheRepository<Customer> {

    public Customer findById(Long id) {
        return find("id", id).firstResult();
    }

}
