package io.bgama.resources;

import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import io.bgama.api.service.AccountServiceAccess;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountServiceAccess accountService;

    @POST
    public Response createAccount(AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.createAccount(accountRequest);
        return Response.status(Response.Status.CREATED).entity(accountResponse).build();
    }

    @GET
    @Path("/{accountId}")
    public Response getAccountDetails(@PathParam("accountId") Long accountId) {
        AccountResponse accountResponse = accountService.getAccountDetails(accountId);
        return Response.ok(accountResponse).build();
    }
}
