
package edu.ch.hajo.validation;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.validation.constraints.*;

/**
 * Bean validation using Bean Validation API
 * 
 * If validation fail messages sent to GUI <h:message(s) ..> tags
 * 
 * @author hajo
 */
@Named("validate")
@SessionScoped
public class ValidateBean implements Serializable {

    //@Digits(integer=2, fraction=0)
    @Min(value = 1, message = "To small")
    @Max(value = 10, message = "To big")
    // See also @Min.List
    private int value;
    @NotNull
    @Size(min = 1, max = 8, message = "Must use 1-8 chars")
    private String data;
    // Also @Past @Future
    @Pattern(regexp = "[A_Z][a-z]*")
    private String pattern;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void actionListener(ActionEvent e) {
        Logger.getAnonymousLogger().log(Level.INFO, "Clicked {0}", e.getComponent().getId());
    }
}
