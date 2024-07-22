package io.bgama.dto;

import io.bgama.dto.account.AccountResponse;
import io.bgama.dto.customer.CustomerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerResponseTest {

    @Test
    public void testSetters() {
        CustomerResponse response = new CustomerResponse(1L, "Bruno", "Gama");
        response.setId(11L);
        response.setName("Test");
        response.setSurname("LastTest");

        Assertions.assertEquals(11L, response.getId());
        Assertions.assertEquals("Test", response.getName());
        Assertions.assertEquals("LastTest", response.getSurname());
    }
}
