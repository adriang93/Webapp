
package edu.chl.hajo.jsfs.core;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Shop;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/**
 * A wrapper for the shop (will live in application scope)
 * @author hajo
 */
@Named
@ApplicationScoped 
public class SingletonShop implements Serializable {

    private final IShop shop = Shop.newInstance();

    public IShop getShop() {
        return shop;
    }

}
