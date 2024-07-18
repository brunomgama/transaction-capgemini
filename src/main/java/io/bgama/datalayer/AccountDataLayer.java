package io.bgama.datalayer;

import io.bgama.entity.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountDataLayer implements PanacheRepository<Account> {

}