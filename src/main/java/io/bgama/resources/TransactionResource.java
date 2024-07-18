package io.bgama.resources;

import io.bgama.dto.transaction.TransactionRequest;
import io.bgama.dto.transaction.TransactionResponse;
import io.bgama.api.service.TransactionServiceAccess;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionServiceAccess transactionService;

    @POST
    public Response createTransaction(TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        return Response.status(Response.Status.CREATED).entity(transactionResponse).build();
    }

    @GET
    @Path("/{transactionId}")
    public Response getTransactionDetails(@PathParam("transactionId") Long transactionId) {
        TransactionResponse transactionResponse = transactionService.getTransactionDetails(transactionId);
        return Response.ok(transactionResponse).build();
    }
}
