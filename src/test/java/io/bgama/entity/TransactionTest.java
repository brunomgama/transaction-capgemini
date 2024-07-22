package io.bgama.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void testGetSetId() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        Assertions.assertEquals(1L, transaction.getId());
    }

    @Test
    public void testGetSetAccountId() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(1L);
        Assertions.assertEquals(1L, transaction.getAccountId());
    }

    @Test
    public void testGetSetIsDebit() {
        Transaction transaction = new Transaction();
        transaction.setDebit(true);
        Assertions.assertTrue(transaction.getDebit());
    }

    @Test
    public void testGetSetAmount() {
        Transaction transaction = new Transaction();
        transaction.setAmount(Double.valueOf(2.25));
        Assertions.assertEquals(Double.valueOf(2.25), transaction.getAmount());
    }
}
