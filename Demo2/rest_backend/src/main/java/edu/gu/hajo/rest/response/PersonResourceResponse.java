package edu.gu.hajo.rest.response;

import edu.gu.hajo.core.Person;
import edu.gu.hajo.core.PersonRegistry;
import java.net.URI;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;



/**
 * Async root resource class with custom responses (returning Response object)
 * (for non async, see PersonConditionalResource)
 *
 * Exception handling - Throw WebApplicationException or - Use Response class to
 * send custom HTTP response (400-599)
 *
 * @author hajo
 */
@Path("/response")
public class PersonResourceResponse {

    private final static Logger log = Logger.getAnonymousLogger();
    private final static PersonRegistry reg = PersonRegistry.INSTANCE;
    // Helper class used to build URI's. Injected by container 
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findAll() {
   
        Collection<Person> ps = reg.findAll();

        // return Response.ok(ps).build();   Doesn't work!
        // Must do like this. NOTE: Anonymous sub class {} last...
        GenericEntity<Collection<Person>> ge = new GenericEntity<Collection<Person>>(ps) {
        };
        // The ok() call will return a ResponseBuilder object. The
        // build() call will create the Response object (!?)
        return Response.ok(ge).build();
    }
    
    @GET
    @Path(value = "{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response find( @PathParam(value = "id") Long id) {
    
        Person p = reg.find(id);
        if (p != null) {
            return Response.ok(p).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path(value = "count")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response count() {
       
        log.log(Level.INFO, "Count");
        int c = reg.count();
        // Can't return primitive types, create object
        JsonObject value = Json.createObjectBuilder().add("value", c).build();
        return Response.ok(value).build();
    }

    @DELETE
    @Path(value = "{id}")
    public Response delete(@PathParam(value = "id") final Long id) {
      
        log.log(Level.INFO, "Delete {0}", id);
        try {
            reg.delete(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path(value = "{id}")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response update(@PathParam(value = "id") Long id, @FormParam(value = "fname") String fname, @FormParam(value = "age") int age) {
      log.log(Level.INFO, "Put {0}", id);
        try {
            Person old = reg.find(id);
            reg.update(new Person(id, fname, age));
            // Convert old to HTTP response
            return Response.ok(old).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
 
    @POST
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam(value = "id") Long id, @FormParam(value = "fname") String fname, @FormParam(value = "age") int age) {
     log.log(Level.INFO, "Insert {0} {1} {2}", new Object[]{id, fname, age});
        Person p = new Person(id, fname, age);
        try {
            reg.create(p);
            // Tell client where new resource is (URI to)
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build(p);
            return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path(value = "range")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam(value = "fst") int fst, @QueryParam(value = "count") int count) {
       
        Collection<Person> ps = reg.findRange(fst, count);
        GenericEntity<Collection<Person>> ge = new GenericEntity<Collection<Person>>(ps) {
        };
        return Response.ok(ge).build();
    }

}
