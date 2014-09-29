package edu.chl.hajo.hateoas;

import edu.chl.hajo.hateoas.core.Car;
import edu.chl.hajo.hateoas.core.PersonRegistry;
import edu.chl.hajo.hateoas.core.Person;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * This is the same as the simple person database CRUD example but using HATEOAS
 *
 * This uses XML as return
 *
 * @author hajo
 */
@Path("/persons")
public class PersonResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private final static PersonRegistry reg = PersonRegistry.INSTANCE;

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public LinkedPersonList selectRange(@QueryParam("start") int start,
            @QueryParam("count") @DefaultValue("2") int count,
            @Context UriInfo uriInfo) {

        log.log(Level.INFO, "*** Select start={0} count={1}", new Object[]{start, count});

        // Building the template. Actual values populated 
        // when next and prev links added, below
        UriBuilder preNextBuilder = uriInfo.getAbsolutePathBuilder();
        preNextBuilder.queryParam("start", "{start}");
        preNextBuilder.queryParam("size", "{size}");

        // Final result to return
        LinkedPersonList lpersons = new LinkedPersonList();
        String uri = null;

        // Previous link 
        if (start > 0) {
            int previous = start - count;
            if (previous < 0) {
                previous = 0;
            }
            // Here we use the template again to set start and size
            uri = preNextBuilder.clone().build(previous, count).toString();
        } else {
            // Same uri as we came from i.e. "http://localhost:8080/hateoas/webresources/persons"
            uri = uriInfo.getBaseUriBuilder().segment("persons").build().toString();
        }
        Logger.getAnonymousLogger().log(Level.INFO, "*** Prev {0}", uri);
        lpersons.setPrev(new AtomLink("prev", uri, MediaType.APPLICATION_JSON));

        // Next link
        if (start + count < reg.count()) {
            int n = start + count;
            // Here we use the template to set start and size
            // (clone else template parameters replaced)
            uri = preNextBuilder.clone().build(n, count).toString();
        } else {
            // Same uri as we came from i.e. "http://localhost:8080/hateoas/webresources/persons"
            uri = uriInfo.getBaseUriBuilder().segment("persons").build().toString(); // TODO !!

        }
        Logger.getAnonymousLogger().log(Level.INFO, "*** Next {0}", uri);
        lpersons.setNext(new AtomLink("next", uri, MediaType.APPLICATION_JSON));

        // Add persons
        for (Person p : reg.select(start, count)) {
            // Links to details
            //URI detailUri= b2.clone().build(p.getPnumb());
            LinkedPerson lp = new LinkedPerson(p);
            // Add all links for the person
            Car c = p.getCar();

            // This yields "http://localhost:8080/hateoas/webresources"
            UriBuilder carBuilder = uriInfo.getBaseUriBuilder();
            if (c != null) {
                String regnr = c.getRegnr();
                URI carURI = carBuilder.segment("cars").segment(regnr).build();
                lp.addLink(new AtomLink("car", carURI.toString(), MediaType.APPLICATION_JSON));
            }
            lpersons.add(lp);
        }
        return lpersons;
    }
}
