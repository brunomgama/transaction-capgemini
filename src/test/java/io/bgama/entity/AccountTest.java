package io.bgama.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testGetSetId() {
        Account account = new Account();
        account.setId(1L);
        Assertions.assertEquals(1L, account.getId());
    }

    @Test
    public void testGetSetCustomerId() {
        Account account = new Account();
        account.setCustomerId(1L);
        Assertions.assertEquals(1L, account.getCustomerId());
    }

    @Test
    public void testGetSetBalance() {
        Account account = new Account();
        account.setBalance(Double.valueOf(2.25));
        Assertions.assertEquals(Double.valueOf(2.25), account.getBalance());
    }
}
