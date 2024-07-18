package io.bgama.dto.transaction;

public class TransactionResponse {
    private Long id;
    private Long accountId;
    private Boolean isDebit;
    private Long amount;

    public TransactionResponse(Long id, Long accountId, Boolean isDebit, Long amount) {
        this.id = id;
        this.accountId = accountId;
        this.isDebit = isDebit;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
