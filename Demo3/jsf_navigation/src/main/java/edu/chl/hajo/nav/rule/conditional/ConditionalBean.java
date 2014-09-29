
package edu.chl.hajo.nav.rule.conditional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named("conditional")
@RequestScoped
public class ConditionalBean {

    private boolean boolval;

    public boolean isBoolval() {
        return boolval;
    }

    public void setBoolval(boolean boolval) {
        this.boolval = boolval;
    }

    /** Creates a new instance of ConditionalBean */
    public ConditionalBean() {
    }

}
