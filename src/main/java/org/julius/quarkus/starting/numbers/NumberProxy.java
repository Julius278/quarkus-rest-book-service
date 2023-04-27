package org.julius.quarkus.starting.numbers;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "number.proxy")
@Path("/api/numbers")
public interface NumberProxy {

    @Path("/random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    IsbnThirteen generateRandomIsbnThirteen();
}
