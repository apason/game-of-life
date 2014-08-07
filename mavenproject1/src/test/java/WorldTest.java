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
public class WorldTest {
    
    World world1;
    World world2;
    Rules rules1;
    Rules rules2;
    Rules rules3;
    Rules[] rules = new Rules[3];
    
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
        rules1=new Rules(3,6);
        rules2=new Rules(5,14);
        rules3=new Rules(8,20);
        rules1.setPriority(1);
        rules2.setPriority(2);
        rules3.setPriority(3);
        rules[0]=rules1;
        rules[1]=rules2;
        rules[2]=rules3;
        world1=new World(2,rules);
        world2=new World(4,rules);
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                world1.setCell(j, i, new Cell(null));
        
        world1.getWorld()[0][0].setRules(rules1);
        world1.getWorld()[1][0].setRules(rules1);
        world1.getWorld()[1][1].setRules(rules1);
        
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                world2.setCell(j, i, new Cell(null));
        
        world2.getWorld()[0][0].setRules(rules1);
        world2.getWorld()[0][1].setRules(rules1);
        world2.getWorld()[0][3].setRules(rules3);
        world2.getWorld()[1][1].setRules(rules1);
        world2.getWorld()[1][2].setRules(rules1);
        world2.getWorld()[1][3].setRules(rules2);
        world2.getWorld()[2][2].setRules(rules1);
        world2.getWorld()[2][3].setRules(rules2);
        world2.getWorld()[3][0].setRules(rules2);
        world2.getWorld()[3][1].setRules(rules2);
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
    public void test(){
        
    }
//    @Test
//    public void constructorWorksProperly(){
//        assertEquals(world1.getRules(), rules);
//        assertEquals(world1.getWorld(), world1);
//        assertEquals(world2.getRules(), rules);
//        assertEquals(world2.getWorld(), world2);
//    }
    
    @Test
    public void countNearbysIn4CellsWorld(){
        assertEquals(2, world1.countNearbys(0, 0));
        assertEquals(3, world1.countNearbys(0,1));
        assertEquals(2, world1.countNearbys(1,0));
        assertEquals(2, world1.countNearbys(1,1));
    }
    
    @Test
    public void biggestPriorityIn4CellsWorld(){
        assertEquals(1,world1.getBiggestPriority(0, 0));
        assertEquals(1,world1.getBiggestPriority(0, 1));
        assertEquals(1,world1.getBiggestPriority(1, 0));
        assertEquals(1,world1.getBiggestPriority(1, 1));
    }
    
    @Test
    public void evolve4CellsWorld(){
        world1.evolve();
        
        world1.printWorld();
        
        assertEquals(1,world1.countNearbys(0, 0));
        assertEquals(0,world1.countNearbys(0, 1));
        assertEquals(1,world1.countNearbys(1, 0));
        assertEquals(1,world1.countNearbys(1, 1));
        
        assertEquals(1,world1.getBiggestPriority(0, 0));
        assertEquals(0,world1.getBiggestPriority(0, 1));
        assertEquals(1,world1.getBiggestPriority(1, 0));
        assertEquals(1,world1.getBiggestPriority(1, 1));
    }
    
    @Test
    public void countNearbysIn16CellsWorld(){
        assertEquals(2,world2.countNearbys(0, 0));
        assertEquals(3,world2.countNearbys(0, 1));
        assertEquals(8,world2.countNearbys(0, 2));
        assertEquals(3,world2.countNearbys(0, 3));
        assertEquals(3,world2.countNearbys(1, 0));
        assertEquals(4,world2.countNearbys(1, 1));
        assertEquals(10,world2.countNearbys(1, 2));
        assertEquals(7,world2.countNearbys(1, 3));
        assertEquals(5,world2.countNearbys(2, 0));
        assertEquals(7,world2.countNearbys(2, 1));
        assertEquals(8,world2.countNearbys(2, 2));
        assertEquals(4,world2.countNearbys(2, 3));
        assertEquals(2,world2.countNearbys(3, 0));
        assertEquals(3,world2.countNearbys(3, 1));
        assertEquals(5,world2.countNearbys(3, 2));
        assertEquals(3,world2.countNearbys(3, 3));
    }
    @Test
    public void biggestPriorityIn16CellsWorld(){
        assertEquals(1,world2.getBiggestPriority(0, 0));
        assertEquals(1,world2.getBiggestPriority(0, 1));
        assertEquals(3,world2.getBiggestPriority(0, 2));
        assertEquals(2,world2.getBiggestPriority(0, 3));
        assertEquals(1,world2.getBiggestPriority(1, 0));
        assertEquals(1,world2.getBiggestPriority(1, 1));
        assertEquals(3,world2.getBiggestPriority(1, 2));
        assertEquals(3,world2.getBiggestPriority(1, 3));
        assertEquals(2,world2.getBiggestPriority(2, 0));
        assertEquals(2,world2.getBiggestPriority(2, 1));
        assertEquals(2,world2.getBiggestPriority(2, 2));
        assertEquals(2,world2.getBiggestPriority(2, 3));
        assertEquals(2,world2.getBiggestPriority(3, 0));
        assertEquals(2,world2.getBiggestPriority(3, 1));
        assertEquals(2,world2.getBiggestPriority(3, 2));
        assertEquals(2,world2.getBiggestPriority(3, 3));
       
    }
//    @Test
//    public void evolve16CellsWorld(){
//        world2.evolve();
//        
//        world2.printWorld();
//        
//        assertEquals(3,world2.countNearbys(0, 0));
//        assertEquals(8,world2.countNearbys(0, 1));
//        assertEquals(7,world2.countNearbys(0, 2));
//        assertEquals(8,world2.countNearbys(0, 3));
//        assertEquals(6,world2.countNearbys(1, 0));
//        assertEquals(14,world2.countNearbys(1, 1));
//        assertEquals(12,world2.countNearbys(1, 2));
//        assertEquals(9,world2.countNearbys(1, 3));
//        assertEquals(5,world2.countNearbys(2, 0));
//        assertEquals(12,world2.countNearbys(2, 1));
//        assertEquals(13,world2.countNearbys(2, 2));
//        assertEquals(10,world2.countNearbys(2, 3));
//        assertEquals(5,world2.countNearbys(3, 0));
//        assertEquals(8,world2.countNearbys(3, 1));
//        assertEquals(7,world2.countNearbys(3, 2));
//        assertEquals(5,world2.countNearbys(3, 3));
//        
//        assertEquals(1,world2.getBiggestPriority(0, 0));
//        assertEquals(3,world2.getBiggestPriority(0, 1));
//        assertEquals(3,world2.getBiggestPriority(0, 2));
//        assertEquals(3,world2.getBiggestPriority(0, 3));
//        assertEquals(2,world2.getBiggestPriority(1, 0));
//        assertEquals(3,world2.getBiggestPriority(1, 1));
//        assertEquals(3,world2.getBiggestPriority(1, 2));
//        assertEquals(3,world2.getBiggestPriority(1, 3));
//        assertEquals(2,world2.getBiggestPriority(2, 0));
//        assertEquals(3,world2.getBiggestPriority(2, 1));
//        assertEquals(3,world2.getBiggestPriority(2, 2));
//        assertEquals(3,world2.getBiggestPriority(2, 3));
//        assertEquals(2,world2.getBiggestPriority(3, 0));
//        assertEquals(2,world2.getBiggestPriority(3, 1));
//        assertEquals(2,world2.getBiggestPriority(3, 2));
//        assertEquals(2,world2.getBiggestPriority(3, 3));
//    }
}
