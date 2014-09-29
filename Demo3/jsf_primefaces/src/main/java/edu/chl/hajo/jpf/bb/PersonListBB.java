package edu.chl.hajo.jpf.bb;

import edu.chl.hajo.jpf.core.Person;
import edu.chl.hajo.jpf.core.PersonRegistry;
import edu.chl.hajo.jpf.util.LazyPersonDataModel;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author hajo
 */
@Named("persons")
@ViewScoped
public class PersonListBB implements Serializable {

    private static final Logger LOG = Logger.getLogger(PersonListBB.class.getName());

    // Selected users that will be removed 
    private Person[] selectedPersons;
    // Lazy loading user list
    private LazyDataModel<Person> lazyModel;
    // Creating new user
    private Person newPerson = new Person();
    // Selected user that will be updated
    private Person selectedPerson = new Person();
    // Service 
    private PersonRegistry reg;

    protected PersonListBB() {  // Must have
    }

    @Inject
    public PersonListBB(PersonRegistry reg) {
        this.reg = reg;
    }

    @PostConstruct
    public void post() {
        lazyModel = new LazyPersonDataModel(reg);
    }

    public void insertPerson() {
        LOG.log(Level.INFO, "insertPerson {0} count {1}", new Object[]{newPerson, reg.count()});
        reg.create(newPerson);
        newPerson = new Person();  // Reset
    }

    public void updatePerson() {
        reg.update(selectedPerson);
    }

    public void deletePersons() {
        for (Person p : selectedPersons) {
            reg.delete(p.getId());
        }
        LOG.log(Level.INFO, "deletePersons count {1}", reg.count());
    }

    public Person getSelectedPerson() {
        LOG.log(Level.INFO, "getSelectedPerson {0}", selectedPerson);
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
        LOG.log(Level.INFO, "setSelectedPerson {0}", this.selectedPerson);
    }

    public Person[] getSelectedPersons() {
        LOG.log(Level.INFO, "getSelectedPersonS {0}", selectedPersons);
        return selectedPersons;
    }

    public void setSelectedPersons(Person[] selectedPersons) {
        this.selectedPersons = selectedPersons;
        LOG.log(Level.INFO, "setSelectedPersonS {0}", this.selectedPersons);
    }

    public Person getNewPerson() {
        LOG.log(Level.INFO, "getNewPerson {0}", newPerson);
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
        LOG.log(Level.INFO, "setNewPerson {0}", this.newPerson);
    }

    public LazyDataModel<Person> getLazyModel() {
        return lazyModel;
    }

}
