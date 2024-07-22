package io.bgama.resources;

import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
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
public class CustomerResourceTest {

    @InjectMock
    @MockitoConfig
    CustomerServiceAccess customerServiceAccess;

    @Test
    void testCreateCustomer() {
        CustomerRequest accountRequest = new CustomerRequest();
        accountRequest.setName("Test");
        accountRequest.setSurname("User");

        CustomerResponse mockResponse = new CustomerResponse(1L, "Test", "User");

        Mockito.when(customerServiceAccess.createCustomer(Mockito.any(CustomerRequest.class))).thenReturn(mockResponse);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/customer");
        response.then().statusCode(201);
    }

    @Test
    void testCreateCustomer_persistenceException() {
        CustomerRequest accountRequest = new CustomerRequest();
        accountRequest.setName("Test");
        accountRequest.setSurname("User");

        Mockito.when(customerServiceAccess.createCustomer(Mockito.any(CustomerRequest.class))).thenThrow(PersistenceException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/customer");
        response.then().statusCode(500);
    }

    @Test
    void testCreateCustomer_unhandledException() {
        CustomerRequest accountRequest = new CustomerRequest();
        accountRequest.setName("Test");
        accountRequest.setSurname("User");

        Mockito.when(customerServiceAccess.createCustomer(Mockito.any(CustomerRequest.class))).thenThrow(BadRequestException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().post("/customer");
        response.then().statusCode(500);
    }

    @Test
    void testGetCustomerDetails() {
        CustomerResponse mockResponse = new CustomerResponse(1L, "Test", "User");
        Mockito.when(customerServiceAccess.getCustomerDetails(Mockito.anyLong())).thenReturn(mockResponse);

        Response response = given().when().get("/customer/1");
        response.then().statusCode(200);
    }

    @Test
    void testGetCustomerDetails_notFound() {
        Mockito.when(customerServiceAccess.getCustomerDetails(Mockito.anyLong())).thenThrow(NotFoundException.class);

        Response response = given().when().get("/customer/1");
        response.then().statusCode(404);
    }

    @Test
    void testGetCustomerDetails_unhandledException() {
        Mockito.when(customerServiceAccess.getCustomerDetails(Mockito.anyLong())).thenThrow(BadRequestException.class);

        Response response = given().when().get("/customer/1");
        response.then().statusCode(500);
    }

    @Test
    void testGetAllCustomers() {
        CustomerResponse mockResponse = new CustomerResponse(1L, "Test", "User");

        Mockito.when(customerServiceAccess.getAllCustomers()).thenReturn(List.of(mockResponse));

        Response response = given().when().get("/customer");
        response.then().statusCode(200);
    }

    @Test
    void testGetAllCustomers_unhandledException() {
        Mockito.when(customerServiceAccess.getAllCustomers()).thenThrow(BadRequestException.class);

        Response response = given().when().get("/customer");
        response.then().statusCode(500);
    }

    @Test
    void testUpdateCustomerDetails() {
        CustomerRequest accountRequest = new CustomerRequest();
        accountRequest.setName("Test");
        accountRequest.setSurname("User");

        CustomerResponse mockResponse = new CustomerResponse(1L, "Test", "User");

        Mockito.when(customerServiceAccess.updateCustomerDetails(Mockito.anyLong(), Mockito.any(CustomerRequest.class))).thenReturn(mockResponse);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().put("/customer/1");
        response.then().statusCode(200);
    }

    @Test
    void testUpdateCustomerDetails_notFound() {
        CustomerRequest accountRequest = new CustomerRequest();
        accountRequest.setName("Test");
        accountRequest.setSurname("User");

        Mockito.when(customerServiceAccess.updateCustomerDetails(Mockito.anyLong(), Mockito.any(CustomerRequest.class))).thenThrow(NotFoundException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().put("/customer/1");
        response.then().statusCode(404);
    }

    @Test
    void testUpdateCustomerDetails_unhandledException() {
        CustomerRequest accountRequest = new CustomerRequest();
        accountRequest.setName("Test");
        accountRequest.setSurname("User");

        Mockito.when(customerServiceAccess.updateCustomerDetails(Mockito.anyLong(), Mockito.any(CustomerRequest.class))).thenThrow(BadRequestException.class);

        Response response = given().body(accountRequest).contentType(ContentType.JSON).when().put("/customer/1");
        response.then().statusCode(500);
    }

    @Test
    void testDeleteAccount() {
        Response response = given().when().delete("/customer/1");
        response.then().statusCode(200);
    }

    @Test
    void testDeleteAccount_notFound() {
        Mockito.doThrow(NotFoundException.class).when(customerServiceAccess).deleteCustomer(Mockito.anyLong());

        Response response = given().when().delete("/customer/1");
        response.then().statusCode(404);
    }

    @Test
    void testDeleteAccount_unhandledException() {
        Mockito.doThrow(BadRequestException.class).when(customerServiceAccess).deleteCustomer(Mockito.anyLong());

        Response response = given().when().delete("/customer/1");
        response.then().statusCode(500);
    }
}
