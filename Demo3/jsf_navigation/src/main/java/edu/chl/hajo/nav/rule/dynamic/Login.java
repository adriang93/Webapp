
package edu.chl.hajo.nav.rule.dynamic;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named("login")
@RequestScoped
public class Login {

    private String id;
    private String password;

    public String doLogin(){
        if( id.equals("aaa") && password.equals("111")){
            return "success";
        }else{
            return "failure";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** Creates a new instance of Login */
    public Login() {
    }



}
