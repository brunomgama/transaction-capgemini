package io.bgama.dto;

import io.bgama.dto.transaction.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionResponseTest {

    @Test
    public void testSetters() {
        TransactionResponse response = new TransactionResponse(1L, 2L, true, Double.valueOf(2.25));
        response.setId(11L);
        response.setAccountId(22L);
        response.setIsDebit(false);
        response.setAmount(Double.valueOf(22.25));

        Assertions.assertEquals(11L, response.getId());
        Assertions.assertEquals(22L, response.getAccountId());
        Assertions.assertFalse(response.getIsDebit());
        Assertions.assertEquals(Double.valueOf(22.25), response.getAmount());
    }
}
