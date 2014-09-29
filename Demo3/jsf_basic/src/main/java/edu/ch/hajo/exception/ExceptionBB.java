
package edu.ch.hajo.exception;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * How to propagate an exception to a page
 * 
 * Possible Exceptions:
 * ConverterException
 * UpdateModelException
 * ValidatorException
 * 
 * @author hajo
 */
@Named("ex")
@RequestScoped
public class ExceptionBB {

    public void actionListener(ActionEvent evt) {

        try {
            double d = 1 / 0;
        } catch (Exception e) {
           // Will end up in <h:message(s).../>
            FacesContext.getCurrentInstance().addMessage(null, 
                   new FacesMessage("Div by zero"));
            
           /* Or like this 
            FacesMessage msg = new FacesMessage("Div by zero"); 
            throw new ValidationException(msg);
           */
            
           // Or possible store excepton message in bean, and access later in page
           
         
        }
    }
}
