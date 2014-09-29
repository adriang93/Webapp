package edu.gu.hajo.client;

import javax.ws.rs.core.Response;
import org.junit.Test;

/**
 * Testing the Java client (this is Java SE an ordinary
 * JUnit test, just run it)
 *
 * @author hajo
 */
public class TestPersonRegClient {

    // To get Persons we probably have to implement 
    // a MessageBodyReader. To much work for now
    // use Strings instead.
    @Test
    public void hello() {
        // Using /response URL
        PersonRegClient client = new PersonRegClient();

        // Works. Using /respones in PersonRegClient
        Response r1 = client.selectAll(Response.class);
        // Output: [{"age":11,"fname":"Pelle","id":11},{"age":22,"fname"... ]
        System.out.println(r1.readEntity(String.class));

        Response r2 = client.count(Response.class);
        System.out.println(r2.readEntity(String.class));
      
    }
}
