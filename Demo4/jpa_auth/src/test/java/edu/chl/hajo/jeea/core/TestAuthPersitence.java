package edu.chl.hajo.jeea.core;

import edu.chl.hajo.jeea.auth.AuthDAO;
import edu.chl.hajo.jeea.auth.Groups;
import edu.chl.hajo.jeea.auth.User;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded
 * database) GlassFish must NOT run.
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestAuthPersitence {

    @Inject
    AuthDAO authDAO;
    
    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                // Add all classes
                //.addPackage("edu.chl.hajo.jeea.core")
                .addPackage("edu.chl.hajo.jeea.auth")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before  // Run before each test
    public void before() throws Exception {

    }

    @Test
    public void testPersistAppUser() throws Exception {
        // String login, String passwd, String fname, String dep, SubjectGroup group
        User u = new User("qqq", "111", Groups.USER);
        authDAO.create(u);
        assertTrue(authDAO.find("qqq") != null);
    }

  

}
