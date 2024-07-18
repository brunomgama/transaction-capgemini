package io.bgama.api.service;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;

public interface CustomerServiceAccess {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomerDetails(Long customerId);
}
