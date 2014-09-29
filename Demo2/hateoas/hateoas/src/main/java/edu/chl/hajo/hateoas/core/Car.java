
package edu.chl.hajo.hateoas.core;

import javax.xml.bind.annotation.*;

/**
 *
 * @author hajo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "CarType", propOrder = {
//"regnr",
//  "brand",
// "model",
// "owner"    
//})
public class Car {

    @XmlAttribute(required = true)
    String regnr;
    @XmlElement(required = true)
    String model;

    public Car() {
    }

    public Car(String regnr, String model) {
        this.regnr = regnr;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String getRegnr() {
        return regnr;
    }
}
