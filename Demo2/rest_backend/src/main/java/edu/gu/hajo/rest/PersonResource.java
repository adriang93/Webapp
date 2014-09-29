package edu.gu.hajo.rest;

import edu.gu.hajo.core.Person;
import edu.gu.hajo.core.PersonRegistry;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Basic root resource class (for async, see
 * PersonResourceResponse)
 *
 * Exception handling - Throw WebApplicationException or - Use Response class to
 * send custom HTTP response (400-599)
 *
 * @author hajo
 */
@Path("/persons")
public class PersonResource {

    private static final Logger LOG = Logger.getLogger(PersonResource.class.getName());
    private final static PersonRegistry reg = PersonRegistry.INSTANCE;
  
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Collection<Person> findAll() {
        LOG.log(Level.INFO, "{0}:findAll", this);
        return reg.findAll();
    }
    
    @GET
    @Path("range")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Collection<Person> findRange(@QueryParam("fst") int fst, @QueryParam("count") int count) {
        LOG.log(Level.INFO, "{0}:findRange {1} {2}", new Object[]{this, fst, count});
        return reg.findRange(fst, count);
    }
    

    @GET
    @Path(value = "{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Person find(@PathParam("id") final Long id) {
        LOG.log(Level.INFO, "{0}: find", this);
        // If not found return 204 No content
        return reg.find(id);
    }

    @GET
    @Path(value = "count")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public JsonObject count() {
        LOG.log(Level.INFO, "{0}: count", this);
        int c = reg.count();
        // Can't return primitive types, create object
        JsonObject value = Json.createObjectBuilder().add("value", c).build();
        return value;
    }

    @DELETE
    @Path(value = "{id}")
    public void delete(@PathParam("id") final Long id) {
        LOG.log(Level.INFO, "{0}:delete{1}", new Object[]{this, id});
        reg.delete(id);
    }

    @PUT
    @Path(value = "{id}")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = {MediaType.APPLICATION_JSON})
    public void update(@PathParam("id") final Long id,
            @FormParam("fname") final String fname, @FormParam("age") final int age) {
        LOG.log(Level.INFO, "{0}:update{1}", new Object[]{this, id});
        reg.update(new Person(id, fname, age));
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public void create(@FormParam("id") final Long id,
            @FormParam("fname") final String fname, @FormParam("age") final int age) {
        LOG.log(Level.INFO, "{0}:create {1} {2} {3}", new Object[]{this, id, fname, age});
        Person p = new Person(id, fname, age);
        reg.create(p);
    }

}
