
package edu.chl.hajo.nav.implicit;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named("implicit")
@RequestScoped
public class ImplicitBean {

    public String doIt(){
        // Do some processing
        // and navigate, to.xhtml
        return "to";
    }

}
