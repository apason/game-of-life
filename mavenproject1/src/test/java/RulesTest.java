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
import java.util.ArrayList;

import logic.*;

/**
 *
 * @author apa
 */
public class RulesTest {
    
    Rules rules;
    ArrayList<Integer> birth;
    ArrayList<Integer> die;
    
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
        birth = new ArrayList<Integer>();
        die = new ArrayList<Integer>();
        birth.add(3);
        die.add(1);
        die.add(4);
        die.add(5);
        die.add(6);
        die.add(7);
        die.add(8);
        rules = new Rules(birth,die,1);
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
        assertEquals(birth,rules.getBirth());
        assertEquals(die,rules.getDie());
    }
    
    @Test
    public void priorityGetterWorksProperly(){
        assertEquals(1,rules.getPriority());
    }
}
