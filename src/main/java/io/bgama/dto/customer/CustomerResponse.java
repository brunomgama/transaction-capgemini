package io.bgama.dto.customer;

/**
 * Data transfer object representing a response containing customer details.
 * @author brunogama
 */
public class CustomerResponse {
    private Long id;
    private String name;
    private String surname;

    /**
     * Constructs a CustomerResponse object with the specified details.
     * @param id      The unique identifier of the customer.
     * @param name    The name of the customer.
     * @param surname The surname of the customer.
     */
    public CustomerResponse(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Retrieves the ID of the customer.
     * @return The ID of the customer.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the customer.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

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
