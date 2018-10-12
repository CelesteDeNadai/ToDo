/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import beans.SessionBean;
import java.util.ArrayList;
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
public class ProjectTest {
    
    public ProjectTest() {
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
     * Test of correctLength method, of class Project.
     */
    @Test
    public void testcorrectLength() {
        System.out.println("incorrectLength");
        Project instance = new Project(-1, "ciaoneeee");
        boolean expResult = false;
        boolean result = instance.correctLength();
        assertEquals(true, instance.correctLength());
        
        Project instance2 = new Project(-1, "loooong striiiing to test with more then 30 characters!");
        assertEquals(false, instance2.correctLength());
        
        Project instance3 = new Project(-1, null);
        assertEquals(false, instance2.correctLength());
    }
}
