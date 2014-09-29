package edu.gu.hajo.wss;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import edu.gu.hajo.wss.core.SingletonShop;
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
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service for the product catalogue (an Adapter) We always use
 * Response as return value (probably easier to modify)
 *
 *
 * @author hajo
 */
@Path("products") // Leading trailing slash doesn't matter, see web.xml
public class ProductCatalogueResource {

    private final IShop shop = SingletonShop.INSTANCE.getShop();
    private static final Logger LOG = Logger.getLogger(ProductCatalogueResource.class.getName());
    
    @Context
    private UriInfo uriInfo;
    
   @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JsonObject json) {
        LOG.log(Level.INFO, "{0}:create", this);
        LOG.log(Level.INFO, "Json{0}", json.toString());
        int id = json.getInt("id");
        int price = json.getInt("price");
        Product p = new Product(Long.valueOf(id), json.getString("name"), Double.valueOf(price));
        ProductWrapper pw = new ProductWrapper(p);
        
        try {
           shop.getProductCatalogue().create(p);
           URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build(pw);
           return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {
        LOG.log(Level.INFO, "{0}:findAll", this);
        Collection<Product> ps = shop.getProductCatalogue().findAll();
        GenericEntity<Collection<ProductWrapper>> ge = new GenericEntity<Collection<ProductWrapper>>(ProductWrapper.getList(ps)) {
        };
        return Response.ok(ge).build();
    }
    
     @GET
    @Path("range")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("fst") int fst, @QueryParam("count") int count) {
        LOG.log(Level.INFO, "{0}:findRange {1} {2}", new Object[]{this, fst, count});
        Collection<Product> ps = shop.getProductCatalogue().findRange(fst, count);
        GenericEntity<Collection<ProductWrapper>> ge = new GenericEntity<Collection<ProductWrapper>>(ProductWrapper.getList(ps)) {
        };
        return Response.ok(ge).build();
    }
    
     @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id, @Context Request request) {
        Product p = shop.getProductCatalogue().find(id);
        ProductWrapper pw = new ProductWrapper(p);
        if (p == null) {
           return Response.noContent().build();
        }
        else{
        return Response.ok(pw).build();
        }
        
       /*         String s = ETagGenerator.getETagFor(p);
        EntityTag tag = new EntityTag(s);

        // Etag Will be evaluted agaist  If-None-Match
        // header in request. 
        // Returnvalues: 
        // --- null if preconditions ARE met
        // --- if NOT create a RespondeBuilder with appropriate status
        Response.ResponseBuilder builder = request.evaluatePreconditions(tag);

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
        }*/
    }
    
    @PUT
    @Path(value = "{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
   // @Produces(value = {MediaType.APPLICATION_JSON})
    public Response update(@PathParam(value = "id") Long id, JsonObject json) {
      LOG.log(Level.INFO, "Put {0}", id);
      LOG.log(Level.INFO, "Json{0}", json.toString());
      int price = json.getInt("price");
        try {
          
            Product now = new Product(id,json.getString("name"),Double.valueOf(price));
            ProductWrapper pw = new ProductWrapper(now);
            shop.getProductCatalogue().update(now);
            // Convert old to HTTP response
            return Response.ok(pw).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON})
    public Response count() {
        LOG.log(Level.INFO, "Count");
        int c = shop.getProductCatalogue().count();
        // Can't return primitive types, create object
        JsonObject value = Json.createObjectBuilder().add("value", c).build();
        return Response.ok(value).build();

    }
    
     @DELETE
    @Path(value = "{id}")
    public Response delete(@PathParam(value = "id") final Long id) {
      
        LOG.log(Level.INFO, "Delete {0}", id);
        try {
            shop.getProductCatalogue().delete(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
