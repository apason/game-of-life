package logic;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Sisältää säännöt yhden tyyppiselle solulle
 * @author apa
 */
public class Rules implements Comparable<Rules>, Serializable {
    private final ArrayList<Integer> birth;
    private final ArrayList<Integer> die;
    // priority 0 on kuollut solu
    private final int priority;
    
    /**
     * 
     * @param birth Lista niistä arvoista jotka synnyttävät ko. Rules:lla varustetun solun
     * @param die Lista niistä arvoista jotka tappavat ko. Rules:lla varustetun solun
     * @param priority Ko. Rules:n omaavan solun prioriteetti
     */
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
    
    /**
     * Comparable rajapinnan määrittelemä metodi vertailuja varten
     * @param r2 Rules johon vertailu kohdistuu
     * @return luku joka kuvaa olion luonnollista järjestystä
     */
    @Override
    public int compareTo(Rules r2){
        return this.priority-r2.getPriority();
    }
}

