package edu.chl.hajo.hateoas;

import edu.chl.hajo.hateoas.core.Car;
import edu.chl.hajo.hateoas.core.PersonRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * Simplest possible...
 *
 * @author hajo
 */
@Path("/cars")
public class CarResource {

    private final static Logger log = Logger.getAnonymousLogger();
    private final static PersonRegistry db = PersonRegistry.INSTANCE;

    @GET
    @Path("{pnumb}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Car selectByPk(@PathParam("pnumb") String regnr) {
        log.log(Level.INFO, "selectByPk {0}", regnr);
        return new Car(regnr, "Volvo");
    }
}
