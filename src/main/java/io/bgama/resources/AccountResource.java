package io.bgama.resources;

import io.bgama.api.service.AccountServiceAccess;
import io.bgama.dto.account.AccountRequest;
import io.bgama.dto.account.AccountResponse;
import io.bgama.error.ErrorMessage;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountServiceAccess accountService;

    @POST
    public Response createAccount(AccountRequest accountRequest) {
        try {
            AccountResponse accountResponse = accountService.createAccount(accountRequest);
            return Response.status(Response.Status.CREATED).entity(accountResponse).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.TRANSACTION_FAILED + e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{accountId}")
    public Response getAccountDetails(@PathParam("accountId") Long accountId) {
        try {
            AccountResponse accountResponse = accountService.getAccountDetails(accountId);
            return Response.ok(accountResponse).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.ACCOUNT_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{accountId}")
    public Response updateAccountDetails(@PathParam("accountId") Long accountId, AccountRequest accountRequest) {
        try {
            AccountResponse accountResponse = accountService.updateAccountDetails(accountId, accountRequest);
            return Response.ok(accountResponse).entity("Account with id " + accountId + " has been updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_ACCOUNT_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{accountId}")
    public Response deleteAccount(@PathParam("accountId") Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return Response.status(Response.Status.OK).entity("Account has been deleted").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.ACCOUNT_NOT_FOUND + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR + e.getMessage()).build();
        }
    }
}
