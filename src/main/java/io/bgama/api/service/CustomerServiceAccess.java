package io.bgama.api.service;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;

public interface CustomerServiceAccess {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerDetails(Long customerId);

    CustomerResponse updateCustomerDetails(Long customerId, CustomerRequest customerRequest);

    boolean deleteCustomer(Long customerId);
}
