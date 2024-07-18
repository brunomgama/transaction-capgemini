package io.bgama.dto.account;

import io.bgama.entity.Customer;

public class AccountResponse {
    private Long accountId;
    private Customer customer;
    private Long balance;

    public AccountResponse(Long accountId, Customer customer, Long balance) {
        this.accountId = accountId;
        this.customer = customer;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
