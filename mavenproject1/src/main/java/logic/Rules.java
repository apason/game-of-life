package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Sisältää säännöt yhden tyyppiselle solulle. 
 * Yksi Rules olio määrittää siis yheden "lajin".
 * @author apa
 */
public class Rules implements Comparable<Rules>, Serializable {
    
    /** Lista tapauksista (naapurisolujen prioriteettien summista) jotka saavat "lajin" edustajan eloon. */
    private final ArrayList<Integer> birth;
    /** Lista tapauksista jotka tappavat "lajin" edustajan. */
    private final ArrayList<Integer> die;
    /** "Lajin" prioriteetti. Voidaan samaistaa esim. biomassaan tai paikkaan ravintoketjussa. 
     *  Kuolleella "lajilla"/solulla ei ole prioriteettiä, sillä sen rules viite on null,
     *  @see Cell
     */
    private final int priority;
    
    /**
     * Konstruktori. Ottaa parametreina kaikki Rulesin attribuutit.
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
     * Comparable rajapinnan määrittelemä metodi vertailuja varten.
     * @param r2 Rules johon vertailu kohdistuu
     * @return luku joka kuvaa olion luonnollista järjestystä
     */
    @Override
    public int compareTo(Rules r2){
        return this.priority-r2.getPriority();
    }
    @Override
    public boolean equals(Object o){
        Rules r2;
        if(o==null)
            return false;
        if(o.getClass()!=this.getClass())
            return false;
        r2 = (Rules) o;
        
        sort();
        Collections.sort(r2.getBirth());
        Collections.sort(r2.getDie());
        
        if(!r2.getBirth().equals(birth))
            return false;
        if(!r2.getDie().equals(die))
            return false;
        return r2.getPriority() == priority;
    }

    @Override
    public int hashCode() {
        sort();
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.birth);
        hash = 97 * hash + Objects.hashCode(this.die);
        hash = 97 * hash + this.priority;
        return hash;
    }
    
    private void sort(){
        Collections.sort(die);
        Collections.sort(birth);
    }
}

