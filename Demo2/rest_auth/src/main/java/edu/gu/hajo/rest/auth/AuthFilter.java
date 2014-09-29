package edu.gu.hajo.rest.auth;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 * Protecting the /private resource
 * @author hajo
 */
@Provider
public class AuthFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(AuthFilter.class.getName());

    @Context
    SecurityContext sCtx;

    @Override
    public void filter(ContainerRequestContext rCtx) {
        LOG.log(Level.INFO, "JAX-RS filter hit");
        UriInfo uriInfo = rCtx.getUriInfo();
        LOG.log(Level.INFO, "Request URI {0}", uriInfo.getPath());

        if ("/private".equals(uriInfo.getPath())) {

            String auth = rCtx.getHeaderString("authorization");
            LOG.log(Level.INFO, "auth {0}", auth);
            if (auth == null) {
                rCtx.abortWith(Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("Authorization missing.")
                        .build());
            } else {
                byte[] base64 = DatatypeConverter.parseBase64Binary(auth.split(" ")[1].trim());
                String credentials = new String(base64);
                LOG.log(Level.INFO, "credentials {0}", credentials);
                if (!"qqq:111".equals(credentials)) {
                    rCtx.abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("Bad credentials.")
                            .build());
                }
            }
        } else {
            LOG.log(Level.INFO, "Public resource requested");
        }

    }

}
