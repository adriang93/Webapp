package edu.gu.hajo.jsf.auth;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hajo
 */
@Named
@RequestScoped
//@ViewScoped
public class AuthBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AuthBean.class.getSimpleName());
    private static final long serialVersionUID = 1L;

    private String id;
    private String password;

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "Alive {0}", this);
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(id, password);
            LOG.log(Level.INFO, "*** Login success");
            User user = new User(id);//userService.find(username, password);
            externalContext.getSessionMap().put("user", user);  // Store User in session
            return "success";
        } catch (ServletException e) {
            LOG.log(Level.INFO, "*** Login fail");

            FacesContext.getCurrentInstance().
                    addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Login Failed", null));
            // Must set this (use the Flash-scope) else message
            // wan't survive the redirect (see faces-config.xml)
            externalContext.getFlash().setKeepMessages(true);

        }
        return "fail";
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        LOG.log(Level.INFO, "*** Logout success");
        return "success";
    }

    // ------------------------------
    // Getters & Setters 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
