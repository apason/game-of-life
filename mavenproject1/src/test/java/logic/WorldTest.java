package logic;

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
public class WorldTest {

    ArrayList<Rules> rules;
    World world1;
    World world2;
    Rules rules1;
    Rules rules2;
    Rules rules3;

    public WorldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        rules = new ArrayList<Rules>();
        int[] r1d = {1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        ArrayList<Integer> r1dl = new ArrayList<Integer>();
        ArrayList<Integer> r1bl = new ArrayList<Integer>();
        r1bl.add(3);

        for (int i : r1d) {
            r1dl.add(i);
        }

        rules1 = new Rules(r1bl, r1dl, 1);

        rules.add(rules1);

        world1 = new World(2, rules);

        world1.initializeMap();

        world1.getMap()[0][0].setRules(rules1);
        world1.getMap()[1][0].setRules(rules1);
        world1.getMap()[1][1].setRules(rules1);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorWorksProperly() {
        assertEquals(world1.getRules(), rules);
        assertEquals(2, world1.getMap()[0].length);
        assertNotNull(world1.getMap()[0][0]);
    }

    @Test
    public void settersAndGettersWorksProperly() {
        Cell cell = new Cell(null);
        world1.setCell(0, 0, cell);
        assertEquals(cell, world1.getMap()[0][0]);
        assertEquals(rules, world1.getRules());
    }

    @Test
    public void cloneMapWorksProperly() {
        Cell[][] cloned = world1.cloneMap();
        for (int i = 0; i < world1.getMap().length; i++) {
            for (int j = 0; j < world1.getMap().length; j++) {
                assertEquals(world1.getMap()[i][j].getRules(), cloned[i][j].getRules());
            }
        }
    }

    @Test
    public void evolve4CellsWorld() {
        world1.evolve();

        assertEquals(3, world1.countNearbys(0, 0));
        assertEquals(3, world1.countNearbys(0, 1));
        assertEquals(3, world1.countNearbys(1, 0));
        assertEquals(3, world1.countNearbys(1, 1));

        assertEquals(true, world1.getPrioritys(0, 0).contains(1));
        assertEquals(true, world1.getPrioritys(0, 1).contains(1));
        assertEquals(true, world1.getPrioritys(1, 0).contains(1));
        assertEquals(true, world1.getPrioritys(1, 1).contains(1));

        world1.evolve();

        assertEquals(3, world1.countNearbys(0, 0));
        assertEquals(3, world1.countNearbys(0, 1));
        assertEquals(3, world1.countNearbys(1, 0));
        assertEquals(3, world1.countNearbys(1, 1));

        assertEquals(true, world1.getPrioritys(0, 0).contains(1));
        assertEquals(true, world1.getPrioritys(0, 1).contains(1));
        assertEquals(true, world1.getPrioritys(1, 0).contains(1));
        assertEquals(true, world1.getPrioritys(1, 1).contains(1));

    }

    @Test
    public void eliminate4CellsWorksProperly() {
        world1.eliminate(1, 1, world1.getMap());
        assertEquals(rules1, world1.getMap()[1][1].getRules());
        world1.getMap()[0][0].setRules(null);
        world1.eliminate(1, 0, world1.getMap());
        assertEquals(null, world1.getMap()[1][0].getRules());
        assertEquals(rules1, world1.getMap()[1][1].getRules());
    }

    @Test
    public void giveBirth4CellsWorksProperly() {
        world1.giveBirth(0, 1, world1.getMap());
        assertEquals(rules1, world1.getMap()[0][1].getRules());
        world1.getMap()[0][0].setRules(null);
        world1.getMap()[0][1].setRules(null);
        world1.giveBirth(0, 1, world1.getMap());
        assertEquals(null, world1.getMap()[0][1].getRules());

    }

    @Test
    public void countNearbysIn4CellsWorld() {
        assertEquals(2, world1.countNearbys(0, 0));
        assertEquals(3, world1.countNearbys(0, 1));
        assertEquals(2, world1.countNearbys(1, 0));
        assertEquals(2, world1.countNearbys(1, 1));
    }

    @Test
    public void PrioritysIn4CellsWorld() {
        assertEquals(true, world1.getPrioritys(0, 0).contains(1));
        assertEquals(true, world1.getPrioritys(0, 1).contains(1));
        assertEquals(true, world1.getPrioritys(1, 0).contains(1));
        assertEquals(true, world1.getPrioritys(1, 1).contains(1));
        assertEquals(false, world1.getPrioritys(0, 0).contains(0));
        assertEquals(false, world1.getPrioritys(0, 0).contains(2));
        assertEquals(false, world1.getPrioritys(1, 1).contains(0));
        assertEquals(false, world1.getPrioritys(1, 0).contains(2));

    }


    @Test
    public void initializationWorksProperly() {
        world1.initializeMap();
        for (int i = 0; i < world1.getMap().length; i++) {
            for (int j = 0; j < world1.getMap().length; j++) {
                assertEquals(null, world1.getMap()[i][j].getRules());
            }
        }

    }

    @Test
    public void removeExtrasWorksProperly() {
        world1.getRules().remove(0);
        world1.removeExtras();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertNull(world1.getMap()[i][j].getRules());
            }
        }
    }

    @Test
    public void clearWorksProperly() {
        world1.getRules().remove(0);
        world1.clear();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertNull(world1.getMap()[i][j].getRules());
            }
        }
    }
}
