package edu.chl.hajo.jsfs.ctrl;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.jsfs.view.EditProductBB;
import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import edu.chl.hajo.shop.core.ProductCatalogue;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hajo
 */
@Named
@RequestScoped
public class EditProductCtrl implements Serializable  {

    
    private static final Logger LOG = Logger.getLogger(EditProductCtrl.class.getName());
    private IProductCatalogue reg;
    private EditProductBB productBB;

    protected EditProductCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "PersonDetailCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        LOG.log(Level.INFO, "PersonDetailCtrl to be destroyed {0}", this);
    }
    
    @Inject
    public EditProductCtrl(SingletonShop reg) {
        this.reg = reg.getShop().getProductCatalogue();
    }
    
    // LiKe this --------------------
    
    @Inject
    public void setProductBB(EditProductBB productBB) {
        this.productBB = productBB;
    }
   
    public String save() {
        LOG.log(Level.INFO, "Save: {0}" + productBB);
        reg.create(new Product(productBB.getId(), productBB.getName(), productBB.getPrice()));
        return "productList?faces-redirect=true";
    }
    // ---- or like this 
    /*public String save(PersonDetailBB personBB) {
        LOG.log(Level.INFO, "Save: {0}" + personBB);
        reg.create(new Person(personBB.getId(), personBB.getFname(), personBB.getAge()));
        return "personList?faces-redirect=true";
    }*/
    
    public String update() {
        LOG.log(Level.INFO, "Update: {0}" + productBB);
        reg.update(new Product(productBB.getId(), productBB.getName(), productBB.getPrice()));
        return "productList?faces-redirect=true";
    }

    public void delete() {
        LOG.log(Level.INFO, "Delete: {0}" + productBB);
        reg.delete(productBB.getId());
    }
  
}
