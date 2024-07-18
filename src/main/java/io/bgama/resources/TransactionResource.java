package io.bgama.resources;

import io.bgama.api.service.TransactionServiceAccess;
import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import io.bgama.error.ErrorMessage;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Resource class for handling transaction-related REST endpoints.
 * This class provides endpoints for creating, retrieving, updating, and deleting transactions.
 * It interacts with TransactionServiceAccess for business logic operations.
 * @author brunogama
 */
@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionServiceAccess transactionService;

    /**
     * Endpoint for creating a new transaction.
     * @param transactionRequest The request body containing transaction details.
     * @return                   HTTP response with the created transaction details.
     */
    @POST
    public Response createTransaction(TransactionRequest transactionRequest) {
        try {
            TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
            return Response.status(Response.Status.CREATED).entity(transactionResponse).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.TRANSACTION_FAILED.getMessage() + e.getMessage()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.ACCOUNT_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }

    /**
     * Endpoint for retrieving transaction details by transaction ID.
     * @param transactionId The ID of the transaction to retrieve.
     * @return              HTTP response with the transaction details.
     */
    @GET
    @Path("/{transactionId}")
    public Response getTransactionDetails(@PathParam("transactionId") Long transactionId) {
        try {
            TransactionResponse transactionResponse = transactionService.getTransactionDetails(transactionId);
            return Response.ok(transactionResponse).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.TRANSACTION_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }

    /**
     * Endpoint for updating transaction details by transaction ID.
     * @param transactionId      The ID of the transaction to update.
     * @param transactionRequest The request body containing updated transaction details.
     * @return                   HTTP response with the updated transaction details.
     */
    @PUT
    @Path("/{transactionId}")
    public Response updateTransactionDetails(@PathParam("transactionId") Long transactionId, TransactionRequest transactionRequest) {
        try {
            TransactionResponse transactionResponse = transactionService.updateTransactionDetails(transactionId, transactionRequest);
            return Response.ok(transactionResponse).entity("Transaction with id " + transactionId + " has been updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.TRANSACTION_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }

    /**
     * Endpoint for deleting a transaction by transaction ID.
     * @param transactionId The ID of the transaction to delete.
     * @return              HTTP response indicating success or failure of the deletion.
     */
    @DELETE
    @Path("/{transactionId}")
    public Response deleteTransaction(@PathParam("transactionId") Long transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return Response.status(Response.Status.OK).entity("Transaction has been deleted").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorMessage.TRANSACTION_NOT_FOUND.getMessage() + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorMessage.UNEXPECTED_ERROR.getMessage() + e.getMessage()).build();
        }
    }
}
