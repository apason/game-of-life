/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;

/**
 *
 * @author apa
 */
public class Rules implements Comparable<Rules> {
    private final ArrayList<Integer> birth;
    private final ArrayList<Integer> die;
    // priority 0 on kuollut solu
    private final int priority;
    
    public Rules (ArrayList<Integer> birth, ArrayList<Integer> die, int priority){
        this.birth=birth;
        this.die=die;
        this.priority=priority;
    }
    
    public ArrayList<Integer> getBirth(){
        return this.birth;
    }
    
    public ArrayList<Integer> getDie(){
        return this.die;
    }
    
    public int getPriority(){
        return this.priority;
    }
    
    @Override
    public int compareTo(Rules r2){
        return this.priority-r2.getPriority();
    }
}
