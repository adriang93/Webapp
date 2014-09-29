/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.hajo.shop.core;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian
 */
public class ProductCatalogueTest {
    
    public ProductCatalogueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of newInstance method, of class ProductCatalogue.
     */
    @Test
    public void testNewInstance() {
        System.out.println("newInstance");
        IProductCatalogue expResult = null;
        IProductCatalogue result = ProductCatalogue.newInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByName method, of class ProductCatalogue.
     */
    @Test
    public void testGetByName() {
        System.out.println("getByName");
        String name = "";
        ProductCatalogue instance = null;
        List<Product> expResult = null;
        List<Product> result = instance.getByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
