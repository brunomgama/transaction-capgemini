package io.bgama.resources;

import io.bgama.api.service.TransactionServiceAccess;
import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.MockitoConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TransactionResourceTest {
    @InjectMock
    @MockitoConfig
    TransactionServiceAccess transactionServiceAccess;

    @Test
    void testCreateTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        TransactionResponse mockResponse = new TransactionResponse(1L, 1L, Boolean.TRUE, Double.valueOf(1));

        Mockito.when(transactionServiceAccess.createTransaction(Mockito.any(TransactionRequest.class))).thenReturn(mockResponse);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().post("/transaction");
        response.then().statusCode(201);
    }

    @Test
    void testCreateTransaction_persistenceException() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        TransactionResponse mockResponse = new TransactionResponse(1L, 1L, Boolean.TRUE, Double.valueOf(1));

        Mockito.when(transactionServiceAccess.createTransaction(Mockito.any(TransactionRequest.class))).thenThrow(PersistenceException.class);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().post("/transaction");
        response.then().statusCode(500);
    }

    @Test
    void testCreateTransaction_notFoundException() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        Mockito.when(transactionServiceAccess.createTransaction(Mockito.any(TransactionRequest.class))).thenThrow(NotFoundException.class);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().post("/transaction");
        response.then().statusCode(404);
    }

    @Test
    void testCreateTransaction_unhandledException() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        Mockito.when(transactionServiceAccess.createTransaction(Mockito.any(TransactionRequest.class))).thenThrow(BadRequestException.class);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().post("/transaction");
        response.then().statusCode(500);
    }

    @Test
    void testGetTransactionDetails() {
        TransactionResponse mockResponse = new TransactionResponse(1L, 1L, Boolean.TRUE, Double.valueOf(1));
        Mockito.when(transactionServiceAccess.getTransactionDetails(Mockito.anyLong())).thenReturn(mockResponse);

        Response response = given().when().get("/transaction/1");
        response.then().statusCode(200);
    }

    @Test
    void testGetTransactionDetails_notFound() {
        Mockito.when(transactionServiceAccess.getTransactionDetails(Mockito.anyLong())).thenThrow(NotFoundException.class);

        Response response = given().when().get("/transaction/1");
        response.then().statusCode(404);
    }

    @Test
    void testGetTransactionDetails_unhandledException() {
        Mockito.when(transactionServiceAccess.getTransactionDetails(Mockito.anyLong())).thenThrow(BadRequestException.class);

        Response response = given().when().get("/transaction/1");
        response.then().statusCode(500);
    }

    @Test
    void testGetAllTransactions() {
        TransactionResponse mockResponse = new TransactionResponse(1L, 1L, Boolean.TRUE, Double.valueOf(1));

        Mockito.when(transactionServiceAccess.getAllTransactions()).thenReturn(List.of(mockResponse));

        Response response = given().when().get("/transaction");
        response.then().statusCode(200);
    }

    @Test
    void testGetAllTransactions_unhandledException() {
        Mockito.when(transactionServiceAccess.getAllTransactions()).thenThrow(BadRequestException.class);

        Response response = given().when().get("/transaction");
        response.then().statusCode(500);
    }

    @Test
    void testGetTransactionPerAccount() {
        TransactionResponse mockResponse = new TransactionResponse(1L, 1L, Boolean.TRUE, Double.valueOf(1));

        Mockito.when(transactionServiceAccess.getTransactionPerAccount(1L)).thenReturn(List.of(mockResponse));

        Response response = given().when().get("/transaction/filter/1");
        response.then().statusCode(200);
    }

    @Test
    void testGetTransactionPerAccount_notFoundException() {
        Mockito.when(transactionServiceAccess.getTransactionPerAccount(1L)).thenThrow(NotFoundException.class);

        Response response = given().when().get("/transaction/filter/1");
        response.then().statusCode(404);
    }

    @Test
    void testGetTransactionPerAccount_unhandledException() {
        Mockito.when(transactionServiceAccess.getTransactionPerAccount(1L)).thenThrow(BadRequestException.class);

        Response response = given().when().get("/transaction/filter/1");
        response.then().statusCode(500);
    }

    @Test
    void testUpdateAccountDetails() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        TransactionResponse mockResponse = new TransactionResponse(1L, 1L, Boolean.TRUE, Double.valueOf(1));

        Mockito.when(transactionServiceAccess.updateTransactionDetails(Mockito.anyLong(), Mockito.any(TransactionRequest.class))).thenReturn(mockResponse);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().put("/transaction/1");
        response.then().statusCode(200);
    }

    @Test
    void testUpdateAccountDetails_notFound() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        Mockito.when(transactionServiceAccess.updateTransactionDetails(Mockito.anyLong(), Mockito.any(TransactionRequest.class))).thenThrow(NotFoundException.class);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().put("/transaction/1");
        response.then().statusCode(404);
    }

    @Test
    void testUpdateAccountDetails_unhandledException() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(1L);
        transactionRequest.setAmount(Double.valueOf(1));
        transactionRequest.setIsDebit(true);

        Mockito.when(transactionServiceAccess.updateTransactionDetails(Mockito.anyLong(), Mockito.any(TransactionRequest.class))).thenThrow(BadRequestException.class);

        Response response = given().body(transactionRequest).contentType(ContentType.JSON).when().put("/transaction/1");
        response.then().statusCode(500);
    }

    @Test
    void testDeleteAccount() {
        Response response = given().when().delete("/transaction/1");
        response.then().statusCode(200);
    }

    @Test
    void testDeleteAccount_notFound() {
        Mockito.doThrow(NotFoundException.class).when(transactionServiceAccess).deleteTransaction(Mockito.anyLong());

        Response response = given().when().delete("/transaction/1");
        response.then().statusCode(404);
    }

    @Test
    void testDeleteAccount_unhandledException() {
        Mockito.doThrow(BadRequestException.class).when(transactionServiceAccess).deleteTransaction(Mockito.anyLong());

        Response response = given().when().delete("/transaction/1");
        response.then().statusCode(500);
    }
}
