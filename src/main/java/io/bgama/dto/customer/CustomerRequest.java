package io.bgama.dto.customer;

/**
 * Data transfer object representing a request to create or update a customer.
 * @author brunogama
 */
public class CustomerRequest {
    private String name;
    private String surname;

    /**
     * Retrieves the name of the customer.
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the surname of the customer.
     * @return The surname of the customer.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the customer.
     * @param surname The surname to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
