package edu.chl.hajo.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * Must be logged in to use the service (via twitter for this specific
 * application)
 *
 * @author hajo
 */
@Path("/protected")
public class MyProtectedResource {
    private static final Logger LOG = Logger.getLogger(MyProtectedResource.class.getName());

    
    @GET
    @Produces("application/json")
    public JsonObject getJson() {
        LOG.log(Level.INFO, "MyProtectedResource getJson");
        JsonObject model = Json.createObjectBuilder().add("success", "sucess").build();
        return model;
    }

    // Appliction registred as read only, this should not work!
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {

    }
}
