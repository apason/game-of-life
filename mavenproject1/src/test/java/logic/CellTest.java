package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


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


