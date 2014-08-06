/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

/**
 *
 * @author apa
 */
public class Rules {
    private final int born;
    private final int die;
    private int priority;
    
    public Rules (int birth, int die){
        this.born=birth;
        this.die=die;
        this.priority=0;
    }
    
    public void setPriority(int priority){
        this.priority=priority;
    }
    
    public int getBirth(){
        return this.born;
    }
    
    public int getDie(){
        return this.die;
    }
    
    public int getPriority(){
        return this.priority;
    }
}
