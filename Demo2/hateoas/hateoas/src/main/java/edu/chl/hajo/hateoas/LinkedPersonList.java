/*
 * Wrapper klass for list of LinkedPersons
 * 
 * NOTE: Normally better (fewer technical problems) 
 * to create wrapper classes for lists
 * 
 */
package edu.chl.hajo.hateoas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hajo
 */
@XmlRootElement(name = "linkedpersonlist")
public class LinkedPersonList {
    // For prev and next links

   @XmlElementRef
    List<AtomLink> links = new ArrayList<>(2);
    {
        // Must have else set() wan't work
        links.add(new AtomLink());
        links.add(new AtomLink());
    }
    @XmlElementRef
    List<LinkedPerson> persons = new ArrayList<>();

    public void add(LinkedPerson el) {
        this.persons.add(el);
    }

    public void addAll(List<LinkedPerson> els) {
        this.persons.addAll(els);
    }

    public void setNext(AtomLink l) {
        final int NEXT = 1;
        this.links.set(NEXT, l);
    }

    public void setPrev(AtomLink l) {
        final int PREV = 0;
        this.links.set(PREV, l);
    }
}
