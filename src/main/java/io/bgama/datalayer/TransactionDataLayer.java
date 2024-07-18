package io.bgama.datalayer;

import io.bgama.entity.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionDataLayer implements PanacheRepository<Transaction> {


}
