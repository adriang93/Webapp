package edu.ch.hajo.beans;


import edu.chl.hajo.core.Person;
import edu.chl.hajo.core.PersonRegistry;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Using CDI injection
 *
 * @author hajo
 */
@Named("inject")
@RequestScoped
public class InjectBean implements Serializable{

    //@Inject, here is also possible
    private PersonRegistry preg;  
 
    // Seems like we must have this, so can't create immutable objects
    // VERY BAD, and possible not what Weld specification says??
    protected InjectBean(){
    }
    
    
    @Inject // PREFER THIS! Contructor injection 
    public InjectBean(PersonRegistry preg){
        this.preg = preg;
    }
    
    
    @PostConstruct // Run after everything initiated
    public void postContruct() {
        Person p = preg.selectByPk(11L);
        Logger.getAnonymousLogger().log(Level.INFO, "Person is {0}", p);
    }
     
    @PreDestroy
    public void preDestroy() {
        // Use this for clean up
        Logger.getAnonymousLogger().log(Level.INFO, "{0} preDestroy", this);
    }

    /*
    @Inject, here is also possible
    public void setPersonRegistry(PersonRegistry preg){
         this.preg = preg;
    }*/
   

    public String action() {
        return null;
    }
}
