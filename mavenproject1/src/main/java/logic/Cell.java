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
public class Cell {
    private Rules rules;
    
    public Cell(Rules rules){
        this.rules=rules;
    }
    
    public Rules getRules(){
        return this.rules;
    }
    
    public void setRules(Rules rules){
        this.rules=rules;
    }
    
    public Cell copy(){
        return new Cell(rules);
    }
}
