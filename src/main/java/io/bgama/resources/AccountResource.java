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

import java.util.List;

/**
 * Resource class for handling account-related REST endpoints.
 * This class provides endpoints for creating, retrieving, updating, and deleting accounts.
 * It interacts with AccountServiceAccess for business logic operations.
 * @author brunogama
 */
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountServiceAccess accountService;

    /**
     * Endpoint for creating a new account.
     * @param accountRequest The request body containing account details.
     * @return               HTTP response with the created account details.
     */
    @POST
    public Response createAccount(AccountRequest accountRequest) {
        try {
            AccountResponse accountResponse = accountService.createAccount(accountRequest);
            return Response.status(Response.Status.CREATED).entity(accountResponse).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.TRANSACTION_FAILED.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_NOT_FOUND.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage()).build();
        }
    }

    /**
     * Endpoint for retrieving account details by account ID.
     * @param accountId The ID of the account to retrieve.
     * @return          HTTP response with the account details.
     */
    @GET
    @Path("/{accountId}")
    public Response getAccountDetails(@PathParam("accountId") Long accountId) {
        try {
            AccountResponse accountResponse = accountService.getAccountDetails(accountId);
            return Response.ok(accountResponse).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.ACCOUNT_NOT_FOUND.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage()).build();
        }
    }

    /**
     * Endpoint for retrieving account details by User Id.
     * @param userId    The ID of the user to retrieve.
     * @return          HTTP response with the account details.
     */
    @GET
    @Path("/filter/{userId}")
    public Response getAccountPerUser(@PathParam("userId") Long userId) {
        try {
            List<AccountResponse> accounts = accountService.getAccountPerUser(userId);
            return Response.ok(accounts).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.ACCOUNT_NOT_FOUND.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage()).build();
        }
    }

    /**
     * Endpoint for retrieving all customers.
     * @return HTTP response with the list of all customers.
     */
    @GET
    public Response getAllCustomers() {
        try {
            List<AccountResponse> accounts = accountService.getAllAccounts();
            return Response.ok(accounts).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage()).build();
        }
    }

    /**
     * Endpoint for updating account details by account ID.
     * @param accountId      The ID of the account to update.
     * @param accountRequest The request body containing updated account details.
     * @return               HTTP response with the updated account details.
     */
    @PUT
    @Path("/{accountId}")
    public Response updateAccountDetails(@PathParam("accountId") Long accountId, AccountRequest accountRequest) {
        try {
            AccountResponse accountResponse = accountService.updateAccountDetails(accountId, accountRequest);
            return Response.ok(accountResponse).entity("Account with id " + accountId + " has been updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.CUSTOMER_ACCOUNT_NOT_FOUND.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage()).build();
        }
    }

    /**
     * Endpoint for deleting an account by account ID.
     * @param accountId The ID of the account to delete.
     * @return          HTTP response indicating success or failure of the deletion.
     */
    @DELETE
    @Path("/{accountId}")
    public Response deleteAccount(@PathParam("accountId") Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return Response.status(Response.Status.OK).entity("Account has been deleted").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.ACCOUNT_NOT_FOUND.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage()).build();
        }
    }
}
