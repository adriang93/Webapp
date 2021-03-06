package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;

/**
 * A Product
 * @author hajo
 */
public class Product extends AbstractEntity {

    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
     
    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + name + ", price=" + price + '}';
    }
}
