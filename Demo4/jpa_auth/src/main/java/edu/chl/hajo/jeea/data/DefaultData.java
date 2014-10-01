package edu.chl.hajo.jeea.data;

import edu.chl.hajo.jeea.auth.AuthDAO;
import edu.chl.hajo.jeea.auth.Groups;
import edu.chl.hajo.jeea.auth.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * This is just to put some data into the database
 *
 * @author hajo
 */
@Startup
@Singleton
public class DefaultData {

    private static final Logger LOG = Logger.getLogger(DefaultData.class.getName());

    @Inject
    private AuthDAO authDAO;

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "*** Default data alive");
        createtTestData(); // COMMENT OUT First run (no tables yet)
    }

    @PreDestroy
    public void destroy() {
        LOG.log(Level.INFO, "*** Default data will be destroyed");
        clearTestData();
    }

    private void createtTestData() {
        LOG.log(Level.INFO, "*** Add default data");
        User u = new User("qqq", "111", Groups.ADMIN);
        authDAO.create(u);
        u = new User("www", "222", Groups.USER);
        authDAO.create(u);

    }

    private void clearTestData() {
        authDAO.delete("qqq");
        authDAO.delete("www");

    }

    // If using default digest algorithm 
    //  (also put Hex and UTF-8 in realm configuration
    private String getSHA256(String passwd) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = "admin";
        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }

}
