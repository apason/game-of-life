/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import logic.*;
import interfaces.*;

/**
 *
 * @author apa
 */
public class Main {

    public static void main(String[] args) {
        Session session = new Session();
        Rules rule1;

        int[] r1d = {1, 4, 5, 6, 7, 8};
        ArrayList<Integer> r1dl = new ArrayList<Integer>();
        ArrayList<Integer> r1bl = new ArrayList<Integer>();
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
        
        
    }

}
