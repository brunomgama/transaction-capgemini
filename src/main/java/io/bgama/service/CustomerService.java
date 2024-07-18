package io.bgama.service;

import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerService implements CustomerServiceAccess {

    @Inject
    CustomerDataLayer customerDataLayer;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.name = customerRequest.getName();
        customer.surname = customerRequest.getSurname();
        customerDataLayer.persist(customer);

        return new CustomerResponse(customer.id, customer.name, customer.surname);
    }

    @Override
    public CustomerResponse getCustomerDetails(Long customerId) {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(customer.id, customer.name, customer.surname);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomerDetails(Long customerId, CustomerRequest customerRequest) {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            return null;
        }
        customer.name = customerRequest.getName();
        customer.surname = customerRequest.getSurname();
        customerDataLayer.persist(customer);

        return new CustomerResponse(customer.id, customer.name, customer.surname);
    }

    @Override
    @Transactional
    public boolean deleteCustomer(Long customerId) {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            return false;
        }
        customerDataLayer.delete(customer);
        return true;
    }
}
