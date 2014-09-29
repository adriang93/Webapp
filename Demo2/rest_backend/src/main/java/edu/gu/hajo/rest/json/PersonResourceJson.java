package edu.gu.hajo.rest.json;

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
 * Resource class only handling JSON (also as input parameters)
 *
 * @author hajo
 */
@Path("/json")
public class PersonResourceJson {

    private static final Logger LOG = Logger.getLogger(PersonResourceJson.class.getName());
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
    public Person find(@PathParam(value = "id") final Long id) {
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
    public void delete(@PathParam(value = "id") final Long id) {
        LOG.log(Level.INFO, "{0}:delete {1}", new Object[]{this, id});
        reg.delete(id);
    }

    /*
     *  NOTE: Must have correct <input type="number ... for json.getInt() to work
     */
    @PUT
    @Path(value = "{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public void update(@PathParam(value = "id") final Long id, JsonObject json) {
        LOG.log(Level.INFO, "{0}:update{1}", new Object[]{this, id});
        LOG.log(Level.INFO, "Json{0}", json.toString());
        int age = json.getInt("age");
        Person p = new Person(id, json.getString("fname"), age);
        reg.update(p);
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public void create(JsonObject json) {
        LOG.log(Level.INFO, "{0}:create", this);
        LOG.log(Level.INFO, "Json{0}", json.toString());
        int id = json.getInt("id");
        int age = json.getInt("age");
        Person p = new Person(Long.valueOf(id),
                json.getString("fname"), age);
        reg.create(p);
    }

}
