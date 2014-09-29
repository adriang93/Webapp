
package edu.ch.hajo.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * Possible to decide whats rendered in page
 * @author hajo
 */
@Named( "render")
@SessionScoped
public class RenderBean implements Serializable{ 
    
    private boolean rendered = true;

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
    
    public void actionListener(ActionEvent e){
        this.rendered = !this.rendered; 
    }
}
