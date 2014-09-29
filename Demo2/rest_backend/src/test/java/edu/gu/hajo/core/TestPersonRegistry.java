

package edu.gu.hajo.core;

import org.junit.Test;

/**
 * Bad test
 * @author hajo
 */
public class TestPersonRegistry {
    
   
    // Not a real test just a dump of the content
    //@Test
    public void dump() {
        PersonRegistry pr = PersonRegistry.INSTANCE;
        for( Person p : pr.findAll()){
            System.out.println(p);
        }
    }
    
    @Test
    public void dumpRange() {
        PersonRegistry pr = PersonRegistry.INSTANCE;
        for( Person p : pr.findRange(2,10)){
            System.out.println(p);
        }
    }
    
}
