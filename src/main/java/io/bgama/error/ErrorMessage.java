package io.bgama.error;

/**
 * Enum representing various error messages used in the application.
 * @author brunogama
 */
public enum ErrorMessage {
    UNEXPECTED_ERROR("An unexpected error occurred."),
    TRANSACTION_FAILED("Transaction failed."),
    CUSTOMER_NOT_FOUND("Customer was not found."),
    ACCOUNT_NOT_FOUND("Account was not found."),
    TRANSACTION_NOT_FOUND("Transaction was not found."),
    CUSTOMER_ACCOUNT_NOT_FOUND("Account or Customer was not found.");

    private final String message;

    /**
     * Constructs an ErrorMessage enum with the specified message.
     * @param message The error message.
     */
    ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the error message.
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }
}
