package edu.ch.hajo.beans;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;

import javax.faces.component.UIOutput;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * Component binding (accessing UI component in code)
 * 
 * @author hajo 
 */
@Named("binding")
@SessionScoped
public class BindingBean implements  Serializable {

    // This is the page component with id="txt" (see binding.xhtml)
    private UIOutput txtOutput;

    public UIOutput getTxtOutput() {
        Logger.getAnonymousLogger().log(Level.INFO, "{0} getTxtOutput", txtOutput);
        return txtOutput;
    }

    public void setTxtOutput(UIOutput txtOutput) {
        Logger.getAnonymousLogger().log(Level.INFO, "{0} setTxtOutput", txtOutput);
        this.txtOutput = txtOutput;
    }

    public void actionListener(ActionEvent evt) {
        // Access the component from Java
        txtOutput.setValue("Ooops!");
    }

    /*
     * Redering
     */
    // Name important (is..)
   /*
    public boolean isRendered() {
        return data.length() > 6;  // Silly, for now.
    }*/
}
