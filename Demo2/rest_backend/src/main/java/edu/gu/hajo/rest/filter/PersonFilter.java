package edu.gu.hajo.rest.filter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author hajo
 */

@Provider // Application scoped   
public class PersonFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOG = Logger.getLogger(PersonFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) {
        LOG.log(Level.INFO, "Filter request");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        LOG.log(Level.INFO, "Filter response");
    }

}
