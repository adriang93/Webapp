package edu.gu.hajo.client;

import java.text.MessageFormat;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Jersey REST client generated for REST resource:PersonResource [/persons]<br>
 * USAGE:
 * <pre>
 *        PersonRegClient client = new PersonRegClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author hajo
 */
public class PersonRegClient {
    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/rest_backend/webresources";

    public PersonRegClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("persons"); //response
    }

    public void update(String id) throws ClientErrorException {
        webTarget.path(MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).put(null);
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    public <T> T count(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T selectByPk(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void add() throws ClientErrorException {
        webTarget.request(MediaType.APPLICATION_FORM_URLENCODED).post(null);
    }

    public <T> T selectAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
