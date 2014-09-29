package edu.chl.hajo.hateoas;

import edu.chl.hajo.hateoas.core.Person;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Utility to wrap a single entity (Person) with possible
 * many links to other data (for HATEOAS)
 * 
 * @author hajo
 */
@XmlRootElement(name = "linkedperson")
@XmlType(name = "w") 
/*
 * All classes that can be wrapped must listed be here
 */
public class LinkedPerson{

    @XmlElementRef 
    private List<AtomLink> links = new ArrayList<>();
    @XmlElementRef 
    private Person person;
    
    public LinkedPerson() {
    }
    
    public LinkedPerson(Person p) {
        this.person = p;
    }

    public LinkedPerson(List<AtomLink> links, Person p) {
        this.links = links;
        this.person = p;
    }
    
    public LinkedPerson(AtomLink link, Person person) {
        links.add(link);
        this.person = person;     
    }
    
    public void addLink(AtomLink link) {
        this.links.add(link);
    }
    
    //Can't have ...???
    //@XmlElement
    public Person getData() {
        return person;
    }
   /*
    public void setData(List<T> data) {
        this.data = data;
    }*/
    //@XmlElementRef 
    public List<AtomLink> getLinks() {
        return links;
    }
/*
    public void setLinks(List<AtomLink> links) {
        this.links = links; 
    }
  */  
    
}
