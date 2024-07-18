package io.bgama.service;

import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CustomerService implements CustomerServiceAccess {

    @Inject
    CustomerDataLayer customerDataLayer;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return null;
    }

    @Override
    public CustomerResponse getCustomerDetails(Long customerId) {
        Customer customer = customerDataLayer.findById(customerId);
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(customer.id, customer.name, customer.surname);
    }
}
