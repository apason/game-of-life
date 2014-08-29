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
import interfaces.*;

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void constructorWorksProperly(){
        session1 = new Session(null);
        assertEquals(false ,session1.getRunning());
        //assertNotNull(session1.getPrioritys());
        assertNotNull(session1.getRules());
    }
    
    @Test
    public void createWorldWorksProperly(){
        session1.createWorld(5);
        assertNotNull(session1.getWorld());
    }
    
    @Test
    public void addRuleWorksProperly(){
        
        boolean exception=false;
        
        ArrayList<Integer> r1dl = new ArrayList<Integer>();
        ArrayList<Integer> r1bl = new ArrayList<Integer>();
        ArrayList<Integer> r2dl = new ArrayList<Integer>();
        ArrayList<Integer> r2bl = new ArrayList<Integer>();
        ArrayList<Integer> r3dl = new ArrayList<Integer>();
        ArrayList<Integer> r3bl = new ArrayList<Integer>();
        ArrayList<Integer> r4dl = new ArrayList<Integer>();
        ArrayList<Integer> r4bl = new ArrayList<Integer>();
        
        
        int[] r1d = {1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        
        for(int i : r1d)
            r1dl.add(i);
        r1bl.add(3);
        
        int[] r2d = {1,4,5,6,7,8,9,10,11,12,13,14,19,20,21,22,23,24,25,26,27,28,29,30};
        
        for(int i : r2d)
            r2dl.add(i);
        r2bl.add(15);
        
        int[] r3d = {1,4,5,6,7,8,9,10,25,26,27,28,29,30};
        
        for(int i : r3d)
            r3dl.add(i);
        r3bl.add(15);
        
        int[] r4d = {1,4,5,6,7,8,9,10,11,12,13,14,15,19,20,21};
        
        for(int i : r4d)
            r4dl.add(i);
        r4bl.add(15);
        
        
        Rules rule1 = new Rules(r1bl, r1dl, 1);
        Rules rule2 = new Rules(r2bl, r2dl, 1);
        Rules rule3 = new Rules(r3bl, r3dl, 2);
        Rules rule4 = new Rules(r4bl, r4dl, 3);
        
        try{
            session1.addRule(rule1);
        } catch (Exception e) {
            exception=true;
        }
        
        assertEquals(false, exception);
        exception=false;
        
        try{
            session1.addRule(rule2);
        } catch (Exception e) {
            exception=true;
        }
        
        assertEquals(true, exception);
        exception=false;
        
        try{
            session1.addRule(rule3);
        } catch (Exception e) {
            exception=true;
        }
        
        assertEquals(false, exception);
        exception=false;
        
        try{
            session1.addRule(rule4);
        } catch (Exception e) {
            exception=true;
        }
        
        assertEquals(true, exception);        
    }
    
    @Test
    public void removeRuleWorksProperly(){
        boolean exception=false;
        Rules rule = new Rules(new ArrayList<Integer>(), new ArrayList<Integer>(), 1);
        try{
            session1.addRule(rule);
        }catch(Exception e){
            exception=true;
        }
        assertEquals(false, exception);
        session1.removeRule(rule);
        assertEquals(0,session1.getRules().size());
        
    }
    
    @Test 
    public void stopWorksProperly(){
        assertEquals(false, session1.getRunning());
        session1.setRunning(true);
        assertEquals(true, session1.getRunning());
        session1.stop();
        assertEquals(false, session1.getRunning());
        
    }
    
    
}



