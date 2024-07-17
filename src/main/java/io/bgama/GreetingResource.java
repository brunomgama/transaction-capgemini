package io.bgama;

import io.bgama.datalayer.AccountDataLayer;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    AccountDataLayer accountDataLayer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return accountDataLayer.findById(1L) + "Hello from Quarkus REST";
    }
}
