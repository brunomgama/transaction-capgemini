package io.bgama.dto.account;

public class AccountResponse {
    private Long accountId;
    private String bankName;
    private String iban;
    private Double balance;
    private Long customerId;
    private String iconPath;

    public AccountResponse(Long accountId, String bankName, String iban, Double balance, Long customerId, String iconPath ) {
        this.accountId = accountId;
        this.bankName = bankName;
        this.iban = iban;
        this.balance = balance;
        this.customerId = customerId;
        this.iconPath = iconPath;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
