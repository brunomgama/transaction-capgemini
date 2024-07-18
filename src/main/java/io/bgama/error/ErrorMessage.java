package io.bgama.error;

public enum ErrorMessage {
    UNEXPECTED_ERROR("An unexpected error occurred: "),
    TRANSACTION_FAILED("Transaction failed: "),
    CUSTOMER_NOT_FOUND("Customer was not found: "),
    ACCOUNT_NOT_FOUND("Account was not found: "),
    TRANSACTION_NOT_FOUND("Transaction was not found: "),
    CUSTOMER_ACCOUNT_NOT_FOUND("Account or Customer was not found: ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}