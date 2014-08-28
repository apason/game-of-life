/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import interfaces.Session;
import java.util.ArrayList;
import logic.Rules;
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
public class SaverTest {
    
    Session session;
        Rules rule1;

        ArrayList<Integer> r1dl;
        ArrayList<Integer> r1bl;
    
    public SaverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        session = new Session();

        int[] r1d = {1, 4, 5, 6, 7, 8};
        r1dl = new ArrayList<Integer>();
        r1bl = new ArrayList<Integer>();
        r1bl.add(3);

        for (int i : r1d) {
            r1dl.add(i);
        }

        rule1 = new Rules(r1bl, r1dl, 1);

        try{
            session.addRule(rule1);
        } catch (Exception e){
            System.out.println(e.toString());
            return;
        }
        
        session.createWorld(3);
        
        session.getWorld().getMap()[0][1].setRules(rule1);
        session.getWorld().getMap()[1][1].setRules(rule1);
        session.getWorld().getMap()[2][1].setRules(rule1);
        
        for(int i=0;i<5;i++){
            session.getWorld().printWorld();
            System.out.println("");
            session.getWorld().evolve();
        }
        
        for(int i=0;i<5;i++){
            session.getWorld().printWorld();
            System.out.println("");
            session.getWorld().evolve();
        }
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void saveWorksProperly(){
        Session session2=new Session();
        session.save("testipaska");
        session2.load("testipaska");
        assertTrue(session.equals(session2));
    }
    
}
