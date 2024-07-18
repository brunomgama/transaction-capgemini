package io.bgama.resources;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.api.service.CustomerServiceAccess;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PathParam;
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
                    .entity("Transaction failed: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomerDetails(@PathParam("customerId") Long customerId) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerDetails(customerId);
            if (customerResponse == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
            }
            return Response.ok(customerResponse).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{customerId}")
    public Response updateCustomerDetails(@PathParam("customerId") Long customerId, CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerService.updateCustomerDetails(customerId, customerRequest);
            if (customerResponse == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
            }
            return Response.ok(customerResponse).entity("Customer with id " + customerId + " has been updated.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        try {
            boolean isDeleted = customerService.deleteCustomer(customerId);
            return isDeleted ? Response.status(Response.Status.OK).entity("Customer has been deleted").build()
                    : Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred: " + e.getMessage()).build();
        }
    }
}
