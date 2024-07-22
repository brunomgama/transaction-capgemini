package io.bgama.dto;

import io.bgama.dto.account.AccountResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountResponseTest {

    @Test
    public void testSetters() {
        AccountResponse response = new AccountResponse(1L, 2L, Double.valueOf(2.25));
        response.setAccountId(11L);
        response.setCustomerId(22L);
        response.setBalance(Double.valueOf(22.25));

        Assertions.assertEquals(11L, response.getAccountId());
        Assertions.assertEquals(22L, response.getCustomerId());
        Assertions.assertEquals(Double.valueOf(22.25), response.getBalance());
    }
}
