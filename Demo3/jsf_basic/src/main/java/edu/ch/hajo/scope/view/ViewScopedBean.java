package edu.ch.hajo.scope.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;// NOTE : NOT beans.ViewScoped; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("vs")
@ViewScoped
public class ViewScopedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ViewScopedBean.class.getName());
    private List<String> items;
    private String item;

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "{0} alive", this);
        items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "{0} about to be destroyed", this);
    }

    public void addItem() {
        if (item != null && !item.isEmpty()) {
            items.add(item);
            item = null;
        }
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<String> getItems() {
        return items;
    }

}
