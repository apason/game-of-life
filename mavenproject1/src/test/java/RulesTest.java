/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import logic.*;

/**
 *
 * @author apa
 */
public class RulesTest {
    
    Rules rules;
    
    public RulesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rules = new Rules(3,6);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void constructorWorksProperly(){
        assertEquals(3,rules.getBirth());
        assertEquals(6,rules.getDie());
    }
    
    @Test
    public void prioritySetAndGetWorksProperly(){
        rules.setPriority(0);
        assertEquals(0,rules.getPriority());
    }
}
