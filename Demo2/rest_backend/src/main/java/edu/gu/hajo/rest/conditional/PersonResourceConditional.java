package edu.gu.hajo.rest.conditional;

import edu.gu.hajo.core.Person;
import edu.gu.hajo.core.PersonRegistry;
import java.net.URI;
import java.util.Collection;
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
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * Root resource using conditional GETs and conditional updates. See comments
 * below All methods have Response as return type (wrapping data)
 *
 * USE curl to test
 *
 * @author hajo
 */
@Path("/cond")
public class PersonResourceConditional {

    private static final Logger LOG = Logger.getLogger(PersonResourceConditional.class.getName());
    private final static PersonRegistry reg = PersonRegistry.INSTANCE;
    // Helper class used to build URI's. Injected by container 
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        LOG.log(Level.INFO, "{0}:findAll", this);
        Collection<Person> ps = reg.findAll();
        GenericEntity<Collection<Person>> ge = new GenericEntity<Collection<Person>>(ps) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("range")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("fst") int fst, @QueryParam("count") int count) {
        LOG.log(Level.INFO, "{0}:findRange {1} {2}", new Object[]{this, fst, count});
        Collection<Person> ps = reg.findRange(fst, count);
        GenericEntity<Collection<Person>> ge = new GenericEntity<Collection<Person>>(ps) {
        };
        return Response.ok(ge).build();
    }

    // --------------- CONDITIONAL HERE ----------------
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id, @Context Request request) {
        Person p = reg.find(id);
        if (p == null) {
            // return Response.noContent().build();
        }
        String s = ETagGenerator.getETagFor(p);
        EntityTag tag = new EntityTag(s);

        // Etag Will be evaluted agaist  If-None-Match
        // header in request. 
        // Returnvalues: 
        // --- null if preconditions ARE met
        // --- if NOT create a RespondeBuilder with appropriate status
        ResponseBuilder builder = request.evaluatePreconditions(tag);

        CacheControl cc = new CacheControl();
        cc.setMaxAge(10);  // sec.

        if (builder != null) {  // Precondition NOT met (If-None-Match is false)
            LOG.log(Level.INFO, "Builder NOT null");
            builder.cacheControl(cc);
            // Send 304 "Not Modified"      
            return builder.build();
        } else {
            LOG.log(Level.INFO, "Builder null");
            // Send representation of current data
            return Response.ok(p).tag(tag).cacheControl(cc).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("id") Long id,
            @FormParam("fname") String fname, @FormParam("age") int age) {
        LOG.log(Level.INFO, "Create {0}{1}{2}", new Object[]{id, fname, age});
        Person p = new Person(id, fname, age);
        try {
            reg.create(p);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build(p);
            return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ---------------- CONDITIONAL HERE -------------------------
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, @FormParam("fname") String fname,
            @FormParam("age") int age, @Context Request request) {
        LOG.log(Level.INFO, "Update {0}", id);

        Person p = reg.find(id);
        String s = ETagGenerator.getETagFor(p);
        EntityTag tag = new EntityTag(s);

        ResponseBuilder builder = request.evaluatePreconditions(tag);
        if (builder != null) {  // Precondition NOT met (If-Match false)
            // Send 412 "precondition failed"     
            return builder.build();
        } else {
            try {
                Person old = reg.find(id);
                reg.update(new Person(id, fname, age));
                // Send Ok 200
                return Response.ok(old).build();
            } catch (IllegalArgumentException e) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "Delete {0}", id);
        try {
            reg.delete(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON})
    public Response count() {
        LOG.log(Level.INFO, "Count");
        int c = reg.count();
        // Can't return primitive types, create object
        JsonObject value = Json.createObjectBuilder().add("value", c).build();
        return Response.ok(value).build();

    }
}
