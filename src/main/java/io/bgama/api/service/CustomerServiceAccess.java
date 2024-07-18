package io.bgama.api.service;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import jakarta.ws.rs.NotFoundException;

public interface CustomerServiceAccess {

    CustomerResponse createCustomer(CustomerRequest customerRequest) throws NotFoundException;

    CustomerResponse getCustomerDetails(Long customerId) throws NotFoundException;

    CustomerResponse updateCustomerDetails(Long customerId, CustomerRequest customerRequest) throws NotFoundException;

    void deleteCustomer(Long customerId) throws NotFoundException;
}
