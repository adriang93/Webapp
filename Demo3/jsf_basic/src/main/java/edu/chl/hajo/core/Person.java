package edu.chl.hajo.core;

/**
 * A basic model object 
 * @author hajo
 */
public class Person {

    private Long pnumb;
    private String fname;
    private int age;

   
    public Person() {
    }

    public Person(Long pnumb, String fname, int age) {
        this.pnumb = pnumb;
        this.fname = fname;
        this.age = age;
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
        // Id is Long reference, use equal
        return other.pnumb.equals(this.pnumb);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.pnumb != null ? this.pnumb.hashCode() : 0);
        return hash;
    }
}
