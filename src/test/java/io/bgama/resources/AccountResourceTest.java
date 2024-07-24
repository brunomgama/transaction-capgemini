package io.bgama.resources;

import io.bgama.api.service.AccountServiceAccess;
import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AccountResourceTest {
    @InjectMock
    @MockitoConfig
    AccountServiceAccess accountServiceAccess;

    @Test
    void testCreateAccount() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        AccountResponse mockResponse = new AccountResponse(
                1L, "name", "PT50123",
                Double.valueOf(2.25), 2L, "icon"
        );

        Mockito.when(accountServiceAccess.createAccount(Mockito.any(AccountRequest.class))).thenReturn(mockResponse);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/account");
        response.then().statusCode(201);
    }

    @Test
    void testCreateAccount_persistenceException() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        AccountResponse mockResponse = new AccountResponse(
                1L, "name", "PT50123",
                Double.valueOf(2.25), 2L, "icon"
        );

        Mockito.when(accountServiceAccess.createAccount(Mockito.any(AccountRequest.class))).thenThrow(PersistenceException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/account");
        response.then().statusCode(500);
    }

    @Test
    void testCreateAccount_notFoundException() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        Mockito.when(accountServiceAccess.createAccount(Mockito.any(AccountRequest.class))).thenThrow(NotFoundException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/account");
        response.then().statusCode(404);
    }

    @Test
    void testCreateAccount_unhandledException() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        Mockito.when(accountServiceAccess.createAccount(Mockito.any(AccountRequest.class))).thenThrow(BadRequestException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/account");
        response.then().statusCode(500);
    }

    @Test
    void testGetAccountDetails() {
        AccountResponse mockResponse = new AccountResponse(
                1L, "name", "PT50123",
                Double.valueOf(2.25), 2L, "icon"
        );
        Mockito.when(accountServiceAccess.getAccountDetails(Mockito.anyLong())).thenReturn(mockResponse);

        Response response = given().when().get("/account/1");
        response.then().statusCode(200);
    }

    @Test
    void testGetAccountDetails_notFound() {
        Mockito.when(accountServiceAccess.getAccountDetails(Mockito.anyLong())).thenThrow(NotFoundException.class);

        Response response = given().when().get("/account/1");
        response.then().statusCode(404);
    }

    @Test
    void testGetAccountDetails_unhandledException() {
        Mockito.when(accountServiceAccess.getAccountDetails(Mockito.anyLong())).thenThrow(BadRequestException.class);

        Response response = given().when().get("/account/1");
        response.then().statusCode(500);
    }

    @Test
    void testGetAccountPerUser() {
        AccountResponse mockResponse = new AccountResponse(
                1L, "name", "PT50123",
                Double.valueOf(2.25), 2L, "icon"
        );

        Mockito.when(accountServiceAccess.getAccountPerUser(Mockito.anyLong())).thenReturn(List.of(mockResponse));

        Response response = given().when().get("/account/filter/1");
        response.then().statusCode(200);
    }

    @Test
    void testGetAccountPerUser_notFound() {
        Mockito.when(accountServiceAccess.getAccountPerUser(Mockito.anyLong())).thenThrow(NotFoundException.class);

        Response response = given().when().get("/account/filter/1");
        response.then().statusCode(404);
    }

    @Test
    void testGetAccountPerUser_unhandledException() {
        Mockito.when(accountServiceAccess.getAccountPerUser(Mockito.anyLong())).thenThrow(BadRequestException.class);

        Response response = given().when().get("/account/filter/1");
        response.then().statusCode(500);
    }

    @Test
    void testGetAllCustomers() {
        AccountResponse mockResponse = new AccountResponse(
                1L, "name", "PT50123",
                Double.valueOf(2.25), 2L, "icon"
        );

        Mockito.when(accountServiceAccess.getAllAccounts()).thenReturn(List.of(mockResponse));

        Response response = given().when().get("/account");
        response.then().statusCode(200);
    }

    @Test
    void testGetAllCustomers_unhandledException() {
        Mockito.when(accountServiceAccess.getAllAccounts()).thenThrow(BadRequestException.class);

        Response response = given().when().get("/account");
        response.then().statusCode(500);
    }

    @Test
    void testUpdateAccountDetails() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        AccountResponse mockResponse = new AccountResponse(
                1L, "name", "PT50123",
                Double.valueOf(2.25), 2L, "icon"
        );

        Mockito.when(accountServiceAccess.updateAccountDetails(Mockito.anyLong(), Mockito.any(AccountRequest.class))).thenReturn(mockResponse);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().put("/account/1");
        response.then().statusCode(200);
    }

    @Test
    void testUpdateAccountDetails_notFound() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        Mockito.when(accountServiceAccess.updateAccountDetails(Mockito.anyLong(), Mockito.any(AccountRequest.class))).thenThrow(NotFoundException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().put("/account/1");
        response.then().statusCode(404);
    }

    @Test
    void testUpdateAccountDetails_unhandledException() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankName("new");
        accountRequest.setIban("DE5012");
        accountRequest.setBalance(Double.valueOf(22.25));
        accountRequest.setCustomerId(22L);
        accountRequest.setIconPath("logo");

        Mockito.when(accountServiceAccess.updateAccountDetails(Mockito.anyLong(), Mockito.any(AccountRequest.class))).thenThrow(BadRequestException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().put("/account/1");
        response.then().statusCode(500);
    }

    @Test
    void testDeleteAccount() {
        Response response = given().when().delete("/account/1");
        response.then().statusCode(200);
    }

    @Test
    void testDeleteAccount_notFound() {
        Mockito.doThrow(NotFoundException.class).when(accountServiceAccess).deleteAccount(Mockito.anyLong());

        Response response = given().when().delete("/account/1");
        response.then().statusCode(404);
    }

    @Test
    void testDeleteAccount_unhandledException() {
        Mockito.doThrow(BadRequestException.class).when(accountServiceAccess).deleteAccount(Mockito.anyLong());

        Response response = given().when().delete("/account/1");
        response.then().statusCode(500);
    }
}
