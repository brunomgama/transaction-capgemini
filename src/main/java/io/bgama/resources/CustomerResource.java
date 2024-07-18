package io.bgama.resources;

import io.bgama.dto.customer.CustomerRequest;
import io.bgama.dto.customer.CustomerResponse;
import io.bgama.api.service.CustomerServiceAccess;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
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
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return Response.status(Response.Status.CREATED).entity(customerResponse).build();
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomerDetails(@PathParam("customerId") Long customerId) {
        CustomerResponse customerResponse = customerService.getCustomerDetails(customerId);
        return Response.ok(customerResponse).build();
    }
}
