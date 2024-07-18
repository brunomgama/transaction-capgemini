package io.bgama.service;

import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CustomerService implements CustomerServiceAccess {

    @Inject
    CustomerDataLayer customerDataLayer;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customerDataLayer.persist(customer);

        return new CustomerResponse(customer.getId(), customer.getName(), customer.getSurname());
    }

    @Override
    public CustomerResponse getCustomerDetails(Long customerId) throws NotFoundException {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getSurname());
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomerDetails(Long customerId, CustomerRequest customerRequest) throws NotFoundException {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customerDataLayer.persist(customer);

        return new CustomerResponse(customer.getId(), customer.getName(), customer.getSurname());
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) throws NotFoundException {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            throw new NotFoundException(String.valueOf(Response.Status.NOT_FOUND));
        }
        customerDataLayer.delete(customer);
    }
}
