package edu.chl.hajo.nav.redirect;

import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named("redirect")
public class Redirect {

    public String doIt() {
        return "to";   // See faces-config.xml
        // Also possible
        //return "to?faces-redirect=true";
    }
}
