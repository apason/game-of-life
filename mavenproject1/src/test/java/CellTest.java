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
public class CellTest {
    
    Rules rules;
    Cell cell;
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rules=new Rules(1,2);
        cell=new Cell(rules);
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
        assertEquals(rules,cell.getRules());
    }
    
    @Test
    public void settersAndGettersWorksProperly(){
        Rules rules2 = new Rules(0,5);
        cell.setRules(rules2);
        assertEquals(rules2,cell.getRules());
    }
}
