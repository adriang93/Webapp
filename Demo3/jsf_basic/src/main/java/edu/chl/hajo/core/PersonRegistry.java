package edu.chl.hajo.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Model object NOT a managed bean
 *
 * @author hajo
 */
@Named("personRegistry")
@ApplicationScoped
public class PersonRegistry {

    private final List<Person> data = new ArrayList<>();
    private static final Logger lg = Logger.getAnonymousLogger();

    @PostConstruct
    public void post() {
        lg.log(Level.INFO, "Alive {0}", this);
        data.add(new Person(11L, "Pelle", 11));
        data.add(new Person(22L, "Fia", 22));
        data.add(new Person(33L, "Kalle", 33));
        data.add(new Person(44L, "Siv", 44));
    }

    public void insert(Person p) {
        lg.log(Level.INFO, "Insert {0}", p.getPnumb());
        if (data.contains(p)) {
            throw new IllegalArgumentException("Person already exists id " + p.getPnumb());
        }
        data.add(p);
    }

    public Person selectByPk(Long pnum) {
        lg.log(Level.INFO, "SelectByPk {0}", pnum);
        for (Person p : data) {
            if (p.getPnumb().equals(pnum)) {
                return p;
            }
        }
        return null;
    }

    /*
     * This should be modified to keep existing data, id's normally
     * can't be updated (i.e the pNumb)
     */
    public Person update(Person p) {
        lg.log(Level.INFO, "Update {0}", p.getPnumb());
        Person old = selectByPk(p.getPnumb());
        int i = data.indexOf(old);
        if (i > -1) {
            data.set(i, p);
            return old;
        } else {
            throw new IllegalArgumentException("Person not found id " + p.getPnumb());
        }
    }

    public void delete(Long pnumb) {
        lg.log(Level.INFO, "Delete {0}", pnumb);
        Person p = selectByPk(pnumb);
        if (p != null) {
            data.remove(p);
        } else {
            throw new IllegalArgumentException("Person not found id " + pnumb);
        }
    }

    public List<Person> selectAll() {
        lg.log(Level.INFO, "SelectAll");
        return Collections.unmodifiableList(data);
    }
}
