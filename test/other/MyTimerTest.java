/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import other.MyTimer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tmp-sda-1176
 */
public class MyTimerTest {
    
    public MyTimerTest() {
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
     * Test of addZero method, of class MyTimer.
     */
    @Test
    public void testAddZero() {
        System.out.println("addZero");
        String value = "03";
        String expResult = "03";
        String result = MyTimer.addZero(value);
        assertEquals(expResult, result);
        
        assertEquals("03",  MyTimer.addZero("3"));
        
        assertEquals(null,  MyTimer.addZero("HELLO"));
        
        assertEquals(null,   MyTimer.addZero(null));
    }
    
}
