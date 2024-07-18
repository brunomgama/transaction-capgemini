package io.bgama.dto.account;

public class AccountResponse {
    private Long accountId;
    private Long customerId;
    private Long balance;

    public AccountResponse(Long accountId, Long customerId, Long balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
