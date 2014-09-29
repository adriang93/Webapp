
package edu.gu.hajo.rest.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * Accessible for anyone (test curl)
 *
 * @author hajo
 */
@Path("public")
public class PublicResource {
    private static final Logger LOG = Logger.getLogger(PublicResource.class.getName());

    @Context
    private UriInfo context;

    @GET
    @Produces("application/json")
    public String get() {
        LOG.log(Level.INFO, "Public: Get called"); 
       return "{ \"public\" : \"public\"}";
    }

}
