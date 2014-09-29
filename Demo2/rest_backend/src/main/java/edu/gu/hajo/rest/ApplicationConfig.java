
package edu.gu.hajo.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author hajo
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(edu.gu.hajo.rest.PersonResource.class);
        resources.add(edu.gu.hajo.rest.conditional.PersonResourceConditional.class);
        resources.add(edu.gu.hajo.rest.filter.PersonFilter.class);
        resources.add(edu.gu.hajo.rest.json.PersonResourceJson.class);
        resources.add(edu.gu.hajo.rest.response.PersonResourceResponse.class);
    }
    
}
