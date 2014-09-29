package edu.chl.hajo.hateoas.core;

import javax.xml.bind.annotation.*;

/**
 *
 * @author hajo
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
/*@XmlType(name = "PersonType", propOrder = {
 "fname",
 "age"
 })*/
public class Person {

    @XmlAttribute(required = true)
    private Long pnumb;
    @XmlElement(required = true)
    private String fname;
    @XmlElement(required = true)
    private int age;
    @XmlTransient  // Will not appear in XML (will supply link instaed)
    private Car car;

    // Must have for JAXB
    public Person() {
    }

    public Person(Long pnumb, String fname, int age, Car car) {
        this.pnumb = pnumb;
        this.fname = fname;
        this.age = age;
        this.car = car;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Long getPnumb() {
        return pnumb;
    }

    public void setPnumb(Long pnumb) {
        this.pnumb = pnumb;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // This is special style of equals
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Person)) { // Will handle null
            return false;
        }
        final Person other = (Person) obj;
        // Tricky, id is Long reference
        return other.pnumb.equals(this.pnumb);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.pnumb != null ? this.pnumb.hashCode() : 0);
        return hash;
    }

    public Car getCar() {
        return car;
    }
}
