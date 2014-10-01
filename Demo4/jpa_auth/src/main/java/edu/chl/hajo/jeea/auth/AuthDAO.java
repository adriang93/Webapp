
package edu.chl.hajo.jeea.auth;

import edu.chl.hajo.jeea.persitence.AbstractDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hajo
 */
@Stateless
public class AuthDAO extends AbstractDAO<User, String> {
    private static final Logger LOG = Logger.getLogger(AuthDAO.class.getName());

    @PersistenceContext//(unitName = "jee_auth_pu")
    protected EntityManager em;

    public AuthDAO() {
        super(User.class);
    }

     @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "authDAO alive");
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
