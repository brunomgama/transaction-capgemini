package io.bgama.service;

import io.bgama.datalayer.CustomerDataLayer;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
@TestTransaction
public class CustomerServiceTest {
    @Inject
    CustomerService customerService;
    @InjectMock
    @MockitoConfig
    CustomerDataLayer customerDataLayer;

    @Test
    void testCreateCustomer() {
        CustomerRequest request = new CustomerRequest();
        request.setName("Bruno");
        request.setSurname("Gama");

        customerService.createCustomer(request);
        Mockito.verify(customerDataLayer, Mockito.times(1)).persist(Mockito.any(Customer.class));
    }

    @Test
    void testGetCustomerDetails() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Bruno");
        customer.setSurname("Gama");

        Mockito.when(customerDataLayer.findById(Mockito.anyLong())).thenReturn(customer);

        CustomerResponse response = customerService.getCustomerDetails(2L);
        Assertions.assertEquals(customer.getId(), response.getId());
        Assertions.assertEquals(customer.getName(), response.getName());
        Assertions.assertEquals(customer.getSurname(), response.getSurname());
    }

    @Test
    void testGetAllCustomers() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Bruno");
        customer.setSurname("Gama");

        PanacheQuery<Customer> mockQuery = Mockito.mock(PanacheQuery.class);
        Mockito.when(mockQuery.list()).thenReturn(List.of(customer));
        Mockito.when(customerDataLayer.findAll(Mockito.any(Sort.class))).thenReturn(mockQuery);

        List<CustomerResponse> response = customerService.getAllCustomers();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.size());
    }

    @Test
    void testUpdateCustomerDetails() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Bruno");
        customer.setSurname("Gama");

        Mockito.when(customerDataLayer.findById(Mockito.anyLong())).thenReturn(customer);

        CustomerRequest request = new CustomerRequest();
        request.setName("Bruno");
        request.setSurname("NewSurname");

        CustomerResponse response = customerService.updateCustomerDetails(2L, request);
        Mockito.verify(customerDataLayer, Mockito.times(1)).persist(Mockito.any(Customer.class));
        Assertions.assertEquals(customer.getName(), response.getName());
        Assertions.assertEquals("NewSurname", response.getSurname());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Bruno");
        customer.setSurname("Gama");

        Mockito.when(customerDataLayer.findById(Mockito.anyLong())).thenReturn(customer);

        customerService.deleteCustomer(2L);
        Mockito.verify(customerDataLayer, Mockito.times(1)).delete(Mockito.any(Customer.class));
    }

    @Test
    void testGetCustomerDetails_notFound() {
        Assertions.assertThrows(NotFoundException.class, () -> customerService.getCustomerDetails(2L));
    }
}
