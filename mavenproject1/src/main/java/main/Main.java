/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import logic.*;

/**
 *
 * @author apa
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Rules> rules;
        World world1;
        Rules rules1;

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
        
        world1.printWorld();
        
        System.out.println(""+world1.getPrioritys(0, 0).size());
    }

}
