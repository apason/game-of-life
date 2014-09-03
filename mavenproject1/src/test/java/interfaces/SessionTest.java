package interfaces;

import java.io.File;
import java.util.ArrayList;

import logic.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apa
 */
public class SessionTest {

    Session session1;

    public SessionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        session1 = new Session(null);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorWorksProperly() {
        session1 = new Session(null);
        assertEquals(false, session1.getRunning());
        assertNotNull(session1.getRules());
    }

    @Test
    public void createWorldWorksProperly() {
        session1.createWorld(5);
        assertNotNull(session1.getWorld());
        assertEquals(5, session1.getWorld().getMap().length);
    }

    @Test
    public void addRuleWorksProperly() {

        boolean exception = false;

        ArrayList<Integer> r1dl = new ArrayList<Integer>();
        ArrayList<Integer> r1bl = new ArrayList<Integer>();
        ArrayList<Integer> r2dl = new ArrayList<Integer>();
        ArrayList<Integer> r2bl = new ArrayList<Integer>();
        ArrayList<Integer> r3dl = new ArrayList<Integer>();
        ArrayList<Integer> r3bl = new ArrayList<Integer>();
        ArrayList<Integer> r4dl = new ArrayList<Integer>();
        ArrayList<Integer> r4bl = new ArrayList<Integer>();
        ArrayList<Integer> r5bl = new ArrayList<Integer>();
        ArrayList<Integer> r5dl = new ArrayList<Integer>();

        int[] r1d = {1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

        for (int i : r1d) {
            r1dl.add(i);
        }
        r1bl.add(3);

        int[] r2d = {1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

        for (int i : r2d) {
            r2dl.add(i);
        }
        r2bl.add(15);

        int[] r3d = {1, 4, 5, 6, 7, 8, 9, 10, 25, 26, 27, 28, 29, 30};

        for (int i : r3d) {
            r3dl.add(i);
        }
        r3bl.add(15);

        int[] r4d = {1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 19, 20, 21};

        for (int i : r4d) {
            r4dl.add(i);
        }
        r4bl.add(15);

        Rules rule1 = new Rules(r1bl, r1dl, 1);
        Rules rule2 = new Rules(r2bl, r2dl, 1);
        Rules rule3 = new Rules(r3bl, r3dl, 2);
        Rules rule4 = new Rules(r4bl, r4dl, 3);

        try {
            session1.addRule(rule1);
        } catch (Exception e) {
            exception = true;
        }

        assertEquals(false, exception);
        exception = false;

        try {
            session1.addRule(rule2);
        } catch (Exception e) {
            exception = true;
        }

        assertEquals(true, exception);
        exception = false;

        try {
            session1.addRule(rule3);
        } catch (Exception e) {
            exception = true;
        }

        assertEquals(false, exception);
        exception = false;

        try {
            session1.addRule(rule4);
        } catch (Exception e) {
            exception = true;
        }

        assertEquals(true, exception);
        
       int[] r5d = {1, 4, 5, 6, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        for (int i : r5d) {
            r5dl.add(i);
        }
        r3bl.add(15);
    
        Rules rule5 = new Rules(r5bl, r5dl, 0);
        
        exception = false;

        try {
            session1.addRule(rule5);
        } catch (Exception e) {
            exception = true;
        }

        assertEquals(false, exception);
        assertTrue(session1.getRules().get(0).getPriority()==0);
    
    }

    @Test
    public void removeRuleWorksProperly() {
        boolean exception = false;
        Rules rule = new Rules(new ArrayList<Integer>(), new ArrayList<Integer>(), 1);
        try {
            session1.addRule(rule);
        } catch (Exception e) {
            exception = true;
        }
        assertEquals(false, exception);
        session1.removeRule(rule);
        assertEquals(0, session1.getRules().size());

    }
    
    @Test
    public void stopWorksProperly() {
        assertEquals(false, session1.getRunning());
        session1.setRunning(true);
        assertEquals(true, session1.getRunning());
        session1.stop();
        assertEquals(false, session1.getRunning());

    }

    @Test
    public void saveAndLoadWorksProperly() {
        ArrayList<Integer> dl = new ArrayList<Integer>();
        ArrayList<Integer> bl = new ArrayList<Integer>();
        dl.add(1);
        bl.add(2);
        Rules rule1 = new Rules(dl, bl, 1);
        ArrayList<Rules> rules = new ArrayList<Rules>();
        rules.add(rule1);
        session1.setRules(rules);
        session1.createWorld(3);
        int hc = session1.hashCode();
        File file = new File("testi");
        file.delete();
        session1.save("testi");
        session1 = new Session(null);
        session1.load("testi");
        assertEquals(hc, session1.hashCode());
    }

    @Test
    public void equalsWorksProperly() {
        ArrayList<Integer> dl = new ArrayList<Integer>();
        ArrayList<Integer> bl = new ArrayList<Integer>();
        dl.add(1);
        bl.add(2);
        Rules rule1 = new Rules(bl, dl, 1);
        ArrayList<Rules> rules = new ArrayList<Rules>();
        rules.add(rule1);
        
        session1.setRules(rules);
        session1.createWorld(3);
        
        assertFalse(session1.equals(null));
        assertFalse(session1.equals(new Integer(5)));

        Session session2 = new Session(null);
        assertFalse(session1.equals(session2));
        ArrayList<Integer> dl2 = new ArrayList<Integer>();
        ArrayList<Integer> bl2 = new ArrayList<Integer>();
        dl2.add(2);
        bl2.add(1);
        ArrayList<Rules> rules2 = new ArrayList<Rules>();
        Rules rule2 = new Rules(bl2, dl2, 1);
        rules2.add(rule2);
        session2.setRules(rules2);
        session2.createWorld(3);

        assertTrue(session1.equals(session2));
        session2.createWorld(4);
        assertFalse(session1.equals(session2));
        session2.createWorld(3);
        rules2.add(new Rules(null, null, 2));
        assertFalse(session1.equals(session2));

    }
}
