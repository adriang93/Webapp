
package edu.chl.hajo.jsfs.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Backing bean for editProduct
 * @author hajo
 */
@Named
@RequestScoped
public class EditProductBB  implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditProductBB.class.getName());

    private Long id;
    private String name;
    private double price;

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "EditProductBB alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "EditProductBB to be destroyed {0}", this);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        LOG.log(Level.INFO, "Set id {0}", id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        LOG.log(Level.INFO, "Set name {0}", name);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        LOG.log(Level.INFO, "Set price {0}", price);
    }

    @Override
    public String toString() {
        return "EditProductBB{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }
    
    

    
}
