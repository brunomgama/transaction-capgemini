package io.bgama.service;

import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.entity.Customer;
import io.bgama.error.ErrorMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

/**
 * Service implementation for managing customer-related operations.
 * This class handles creation, retrieval, update, and deletion of customers.
 * It interacts with CustomerDataLayer for data persistence.
 * @author brunogama
 */
@ApplicationScoped
public class CustomerService implements CustomerServiceAccess {

    @Inject
    CustomerDataLayer customerDataLayer;

    /**
     * Creates a new customer based on the provided customer request.
     * @param customerRequest The request containing customer details.
     * @return                The response containing details of the created customer.
     */
    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customerDataLayer.persist(customer);

        return new CustomerResponse(customer.getId(), customer.getName(), customer.getSurname());
    }

    /**
     * Retrieves details of the customer associated with the specified customer ID.
     * @param customerId         The unique identifier of the customer.
     * @return                   The response containing details of the customer.
     * @throws NotFoundException If the customer with the specified ID is not found.
     */
    @Override
    public CustomerResponse getCustomerDetails(Long customerId) throws NotFoundException {
        Customer customer = checkCustomer(customerId);
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getSurname());
    }

    /**
     * Updates details of the customer associated with the specified customer ID.
     * @param customerId         The unique identifier of the customer.
     * @param customerRequest    The request containing updated customer details.
     * @return                   The response containing details of the updated customer.
     * @throws NotFoundException If the customer with the specified ID is not found.
     */
    @Override
    @Transactional
    public CustomerResponse updateCustomerDetails(Long customerId, CustomerRequest customerRequest) throws NotFoundException {
        Customer customer = checkCustomer(customerId);
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customerDataLayer.persist(customer);

        return new CustomerResponse(customer.getId(), customer.getName(), customer.getSurname());
    }

    /**
     * Deletes the customer associated with the specified customer ID.
     * @param customerId         The unique identifier of the customer.
     * @throws NotFoundException If the customer with the specified ID is not found.
     */
    @Override
    @Transactional
    public void deleteCustomer(Long customerId) throws NotFoundException {
        Customer customer = checkCustomer(customerId);
        customerDataLayer.delete(customer);
    }

    /**
     * Checks if the customer with the specified ID exists.
     * @param customerId         The unique identifier of the customer to check.
     * @return                   The customer entity if found.
     * @throws NotFoundException If the customer with the specified ID is not found.
     */
    private Customer checkCustomer(Long customerId) throws NotFoundException {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            throw new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage());
        }
        return customer;
    }
}
