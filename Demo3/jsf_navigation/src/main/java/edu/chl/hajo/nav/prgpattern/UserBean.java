package edu.chl.hajo.nav.prgpattern;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named("userBean")
@RequestScoped
public class UserBean implements Serializable {

    public enum Sex {

        MALE,
        FEMALE
    }

    public Sex[] getSexes() {
        return Sex.values();
    }

    private String firstName;
    private String lastName;
    private Sex sex;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String addConfirmedUser() {
        return "done";
    }

}
