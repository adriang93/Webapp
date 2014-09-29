
package edu.ch.hajo.beans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *  Overloading doesn't work in EL, use different names for methods
 * 
 * @author hajo
 */
@Named("params")
@RequestScoped
public class ParamsBean implements Serializable{ 

    private String data = "no data set yet";
    
    public String doIt(String s) {
        Logger.getAnonymousLogger().log(Level.INFO, "type of param is {0}", s.getClass().getSimpleName());
        Logger.getAnonymousLogger().log(Level.INFO, "*** doIt {0} ", s);
        return null;
    }
    /* Bad error later
    public String doIt(int i) {
        Logger.getAnonymousLogger().log(Level.INFO, "i= " +  i);
        return null;
    }
    */

    public String doOther(Integer i) {
        Logger.getAnonymousLogger().log(Level.INFO, "type of param is {0}", i.getClass().getSimpleName());
        Logger.getAnonymousLogger().log(Level.INFO, "*** doOther {0} ", i);
        return null;
    }

    public String doYetOther(Double d) {
        Logger.getAnonymousLogger().log(Level.INFO, "type of param is {0}", d.getClass().getSimpleName());
        Logger.getAnonymousLogger().log(Level.INFO, "*** doYetOther {0} ", d);
        return null;
    }
    
    
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void actionListener(ActionEvent e) {
        Logger.getAnonymousLogger().log(Level.INFO, "{0} actionListener", this);
    }

    public String action() {
        Logger.getAnonymousLogger().log(Level.INFO, "{0} action", this);
        return "outcome";  
    }

    public void valueChangeListener(ValueChangeEvent e) {
        Logger.getAnonymousLogger().log(Level.INFO, "{0} valueChangeListener", this);
    }
}
