package io.bgama.resources;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.api.service.CustomerServiceAccess;
import io.bgama.error.ErrorMessage;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerServiceAccess customerService;

    @POST
    public Response createCustomer(CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
            return Response.status(Response.Status.CREATED).entity(customerResponse).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.TRANSACTION_FAILED + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomerDetails(@PathParam("customerId") Long customerId) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerDetails(customerId);
            return Response.ok(customerResponse).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{customerId}")
    public Response updateCustomerDetails(@PathParam("customerId") Long customerId, CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerService.updateCustomerDetails(customerId, customerRequest);
            return Response.ok(customerResponse).entity("Customer with id " + customerId + " has been updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return Response.status(Response.Status.OK).entity("Customer has been deleted").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }
}
