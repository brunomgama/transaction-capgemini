package io.bgama.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testGetSetId() {
        Customer customer = new Customer();
        customer.setId(1L);
        Assertions.assertEquals(1L, customer.getId());
    }

    @Test
    public void testGetSetName() {
        Customer customer = new Customer();
        customer.setName("Bruno");
        Assertions.assertEquals("Bruno", customer.getName());
    }

    @Test
    public void testGetSetSurname() {
        Customer customer = new Customer();
        customer.setSurname("Gama");
        Assertions.assertEquals("Gama", customer.getSurname());
    }
}
