package io.bgama.dto.transaction;

public class TransactionRequest {
    private Long accountId;
    private Boolean isDebit;
    private Long amount;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getIsDebit() {
        return isDebit;
    }

    public void setIsDebit(Boolean isDebit) {
        this.isDebit = isDebit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
