package edu.gu.hajo.core;

/**
 * A basic model object prepared for JABX marshaling (i.e. convert to XML
 * or JSON)
 * @author hajo
 */
/*

NOTE: If serving XML we must have annotation like below (if JSON we don't need)

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "PersonType", propOrder = {
    //"pnumb",
    "fname",
    "age"
})*/
public class Person {

    private Long id;
    private String fname;
    private int age;

    // Must have for JAXB
    protected Person() {
    }
  
    public Person(Long id, String fname, int age) {
        this.id = id;
        this.fname = fname;
        this.age = age;
    }

    //@XmlElement(required = true)
    public String getFname() {
        return fname;
    }

    //@XmlAttribute(required = true)
    public Long getId() {
        return id;
    }

    //@XmlElement(required = true)
    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
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
        // Id is Long reference, use equal
        return other.id.equals(this.id);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", fname=" + fname + ", age=" + age + '}';
    }
    
    
}
