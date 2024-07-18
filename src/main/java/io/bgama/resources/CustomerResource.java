package io.bgama.resources;

import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.error.ErrorMessage;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Resource class for handling customer-related REST endpoints.
 * This class provides endpoints for creating, retrieving, updating, and deleting customers.
 * It interacts with CustomerServiceAccess for business logic operations.
 * @author brunogama
 */
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerServiceAccess customerService;

    /**
     * Endpoint for creating a new customer.
     * @param customerRequest The request body containing customer details.
     * @return                HTTP response with the created customer details.
     */
    @POST
    public Response createCustomer(CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
            return Response.status(Response.Status.CREATED).entity(customerResponse).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.TRANSACTION_FAILED.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }

    /**
     * Endpoint for retrieving customer details by customer ID.
     * @param customerId The ID of the customer to retrieve.
     * @return           HTTP response with the customer details.
     */
    @GET
    @Path("/{customerId}")
    public Response getCustomerDetails(@PathParam("customerId") Long customerId) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerDetails(customerId);
            return Response.ok(customerResponse).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }

    /**
     * Endpoint for updating customer details by customer ID.
     * @param customerId      The ID of the customer to update.
     * @param customerRequest The request body containing updated customer details.
     * @return                HTTP response with the updated customer details.
     */
    @PUT
    @Path("/{customerId}")
    public Response updateCustomerDetails(@PathParam("customerId") Long customerId, CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerService.updateCustomerDetails(customerId, customerRequest);
            return Response.ok(customerResponse).entity("Customer with id " + customerId + " has been updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }

    /**
     * Endpoint for deleting a customer by customer ID.
     * @param customerId The ID of the customer to delete.
     * @return           HTTP response indicating success or failure of the deletion.
     */
    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return Response.status(Response.Status.OK).entity("Customer has been deleted").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }
}
