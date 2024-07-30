//package io.bgama.dto;
//
//import io.bgama.dto.account.AccountResponse;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class AccountResponseTest {
//
//    @Test
//    public void testSetters() {
//        AccountResponse response = new AccountResponse(
//                1L, "name", "PT50123",
//                Double.valueOf(2.25), 2L, "icon"
//        );
//
//        response.setAccountId(11L);
//        response.setBankName("new");
//        response.setIban("DE5012");
//        response.setBalance(Double.valueOf(22.25));
//        response.setCustomerId(22L);
//        response.setIconPath("logo");
//
//        Assertions.assertEquals(11L, response.getAccountId());
//        Assertions.assertEquals("new", response.getBankName());
//        Assertions.assertEquals("DE5012", response.getIban());
//        Assertions.assertEquals(Double.valueOf(22.25), response.getBalance());
//        Assertions.assertEquals(22L, response.getCustomerId());
//        Assertions.assertEquals("logo", response.getIconPath());
//    }
//}
