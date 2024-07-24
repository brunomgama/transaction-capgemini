package io.bgama.dto;

import io.bgama.dto.transaction.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionResponseTest {

    @Test
    public void testSetters() {
        TransactionResponse response = new TransactionResponse(
                1L, "loja", 2L, 0L, 0L,
                true,true, Double.valueOf(2.25), false
        );
        response.setId(11L);
        response.setDestination("ikea");
        response.setAccountId(22L);
        response.setTransactionType(1L);
        response.setTransactionCategory(1L);
        response.setState(false);
        response.setIsDebit(false);
        response.setAmount(Double.valueOf(22.25));
        response.setRepetition(false);

        Assertions.assertEquals(11L, response.getId());
        Assertions.assertEquals("ikea", response.getDestination());
        Assertions.assertEquals(22L, response.getAccountId());
        Assertions.assertEquals(1L, response.getTransactionType());
        Assertions.assertEquals(1L, response.getTransactionCategory());
        Assertions.assertFalse(response.getState());
        Assertions.assertFalse(response.getIsDebit());
        Assertions.assertEquals(Double.valueOf(22.25), response.getAmount());
        Assertions.assertFalse(response.getRepetition());
    }
}
