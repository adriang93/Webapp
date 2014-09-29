package edu.chl.hajo.jsfs.view;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import edu.chl.hajo.shop.core.ProductCatalogue;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 *
 * @author hajo
 */
@Named
@ViewScoped
public class ProductListBB implements Serializable {
    
    private IProductCatalogue prod;
    
    private static final Logger LOG = Logger.getLogger(ProductListBB.class.getName());
    private int currentPage;
    private int pageSize = 10;
    private int count;

    protected ProductListBB() {
        // Must have for CDI
    }

    @Inject
    public ProductListBB(SingletonShop reg) {
       this.prod = reg.getShop().getProductCatalogue();
    }

    @PostConstruct
    public void post() {
        count = prod.count();
    }

    public Collection<Product> getList() {
        System.out.println("pageSize: " + count);
        return prod.findRange(pageSize * currentPage, pageSize);
    }

    public void next() {
        if ((currentPage +1) * pageSize < count) {
            currentPage = currentPage + 1;
        }
    }

    public void prev() {
        if (currentPage > 0) {
            currentPage = currentPage - 1;
        }
    }

    // ---- Get/Set -------------
   
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int count() {
        return count;
    }
   

}
