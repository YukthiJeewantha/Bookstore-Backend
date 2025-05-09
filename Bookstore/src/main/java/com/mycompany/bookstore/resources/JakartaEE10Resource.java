package com.mycompany.bookstore.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("/jakartaee10")
@Produces(MediaType.TEXT_PLAIN)
public class JakartaEE10Resource {
    
    @GET
    public Response ping() {
        return Response.ok("ping Jakarta EE").build();
    }
}
