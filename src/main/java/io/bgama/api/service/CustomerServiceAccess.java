package io.bgama.api.service;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import jakarta.ws.rs.NotFoundException;

/**
 * Interface for accessing customer-related operations.
 * @author brunogama
 */
public interface CustomerServiceAccess {

    /**
     * Creates a new customer based on the provided customer request.
     *
     * @param customerRequest       The request containing customer details.
     * @return                      The response containing details of the created customer.
     * @throws NotFoundException    If the associated resource (e.g., account) is not found.
     */
    CustomerResponse createCustomer(CustomerRequest customerRequest) throws NotFoundException;

    /**
     * Retrieves details of the customer associated with the specified customer ID.
     *
     * @param customerId            The unique identifier of the customer.
     * @return                      The response containing details of the customer.
     * @throws NotFoundException    If the customer with the specified ID is not found.
     */
    CustomerResponse getCustomerDetails(Long customerId) throws NotFoundException;

    /**
     * Updates details of the customer associated with the specified customer ID.
     *
     * @param customerId            The unique identifier of the customer.
     * @param customerRequest       The request containing updated customer details.
     * @return                      The response containing details of the updated customer.
     * @throws NotFoundException    If the customer with the specified ID is not found.
     */
    CustomerResponse updateCustomerDetails(Long customerId, CustomerRequest customerRequest) throws NotFoundException;

    /**
     * Deletes the customer associated with the specified customer ID.
     *
     * @param customerId            The unique identifier of the customer.
     * @throws NotFoundException    If the customer with the specified ID is not found.
     */
    void deleteCustomer(Long customerId) throws NotFoundException;
}
