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
public class CellTest {
    
    Rules rules;
    Rules rules2;
    Cell cell;
    ArrayList<Integer> birth;
    ArrayList<Integer> die;
    
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
        rules2= new Rules(new ArrayList<Integer>(), new ArrayList<Integer>(),2);
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
        cell.setRules(rules2);
        assertEquals(rules2,cell.getRules());
    }
    
    @Test
    public void copyWorksProperly(){
        assertEquals(rules,cell.copy().getRules());
    }
}


