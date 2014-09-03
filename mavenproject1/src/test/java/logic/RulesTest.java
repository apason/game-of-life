package logic;

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
        rules = new Rules(birth, die, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorAndGettersWorksProperly() {
        assertEquals(birth, rules.getBirth());
        assertEquals(die, rules.getDie());
    }

    @Test
    public void priorityGetterWorksProperly() {
        assertEquals(1, rules.getPriority());
    }

    @Test
    public void compareToWorksProperly() {
        Rules rules2 = new Rules(null, null, 2);
        Rules rules3 = new Rules(null, null, 1);
        assertTrue(rules.compareTo(rules2) < 0);
        assertTrue(rules.compareTo(rules3) == 0);
    }

    @Test
    public void equalsWorksProperly() {
        assertFalse(rules.equals(null));
        assertFalse(rules.equals(this));
        ArrayList<Integer> r2bl = new ArrayList<Integer>();
        ArrayList<Integer> r2dl = new ArrayList<Integer>();
        r2bl.add(3);
        r2dl.add(1);
        r2dl.add(4);
        r2dl.add(5);
        r2dl.add(6);
        r2dl.add(7);
        r2dl.add(8);
        Rules rules2 = new Rules(r2bl, r2dl, 1);
        assertTrue(rules.equals(rules2));
        r2bl.add(1);
        assertFalse(rules.equals(rules2));
    }

    @Test
    public void hashCodeWorksProperly() {
        ArrayList<Integer> r2bl = new ArrayList<Integer>();
        ArrayList<Integer> r2dl = new ArrayList<Integer>();
        r2bl.add(3);
        r2dl.add(1);
        r2dl.add(4);
        r2dl.add(5);
        r2dl.add(6);
        r2dl.add(7);
        r2dl.add(8);
        Rules rules2 = new Rules(r2bl, r2dl, 1);
        assertEquals(rules.hashCode(), rules2.hashCode());
        r2dl.add(10);
        assertFalse(rules.hashCode() == rules2.hashCode());
    }

    @Test
    public void sortWorksProperly() {
        ArrayList<Integer> r2bl = new ArrayList<Integer>();
        ArrayList<Integer> r2dl = new ArrayList<Integer>();
        r2bl.add(3);
        r2bl.add(1);
        r2dl.add(1);
        r2dl.add(10);
        r2dl.add(5);
        Rules rules2 = new Rules(r2bl, r2dl, 1);
        rules2.sort();
        assertTrue(r2bl.get(0) < r2bl.get(1));
        assertTrue(r2dl.get(0) < r2dl.get(1));
        assertTrue(r2dl.get(1) < r2dl.get(2));
    }
}
