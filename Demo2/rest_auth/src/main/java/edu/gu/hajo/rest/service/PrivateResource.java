

package edu.gu.hajo.rest.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * Private part protected by filter
 *
 * @author hajo
 */
@Path("private")
public class PrivateResource {
    private static final Logger LOG = Logger.getLogger(PrivateResource.class.getName());

   
    @GET 
    @Produces("application/json")
    public String get() {
       LOG.log(Level.INFO, "Private: Get called"); 
       return "{ \"private\" : \"private\"}";
    }

    @PUT
    public void put(String content) {
        LOG.log(Level.INFO, "Private: Put called {0}", content);
    }
}
