package logic;

import java.io.Serializable;

/**
 * Esittää yhtä maailman solua (maailma koostuu soluista) 
 * @see logic.World
 * @author apa
 */
public class Cell implements Serializable {
    /** 
     *  Määrittää solun tyypin. Jos rules attribuutti
     *  on null, niin solu on kuollut.
     */
    private Rules rules;
    
    /**
     * Konstruktori. Solulle annetaan solun tyyppitieto.
     * @param rules Määrittää solun tyypin.
     */
    public Cell(Rules rules){
        this.rules=rules;
    }
    
    public Rules getRules(){
        return this.rules;
    }
    
    public void setRules(Rules rules){
        this.rules=rules;
    }
    
    /**
     * Palauttaa itseään vastaavan (samantilaisen) olion.
     * @return samantilainen olio
     */
    public Cell copy(){
        return new Cell(rules);
    }
}

