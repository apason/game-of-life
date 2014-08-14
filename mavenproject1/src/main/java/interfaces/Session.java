/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author apa
 */

import java.util.ArrayList;
import logic.*;
//sisältää ne tiedot mitä tarvitaan tallennukseen
public class Session {
    
    private boolean running;
    private World world;
    private ArrayList<Integer> prioritys;
    private Cell[][] map; //tarviiko tätä?
    private ArrayList<Rules> rules;
    
    public Session(){
        running=false;
        prioritys=new ArrayList<Integer>();
        rules=new ArrayList<Rules>();
        
        //world cell taulukon alustus, arraylistien alustus, worldin alustus
        
    }
    
    public boolean getRunning(){
        return running;
    }
    
    public World getWorld(){
        return world;
    }
    
    public ArrayList<Integer> getPrioritys(){
        return prioritys;
    }
    
    public ArrayList<Rules> getRules(){
        return rules;
    }
    
    public void createWorld(int size){
        //tutki että rules on ok
        world=new World(size, rules);
        world.initializeMap();
        map=world.getMap();
    }
    
    
    public void addRule(Rules rule) throws Exception {

        for(int i : rule.getBirth())
            if(rule.getDie().contains(i))
                throw(new RuntimeException("conflicts in death and birth conditions"));
        
        for(Rules r : rules){
            if(r.getPriority()==rule.getPriority())
                throw(new RuntimeException("priority must be unique"));
        }
        
        rules.add(rule);
            
    }
    public void removeRule(Rules rule){
        rules.remove(rule);
    }
    
//    public int checkSessionIntegrity(){
//        //mappi alustettu, prioriteetit tiedossa, ja kaikki erilaisia, prioriteetit oikealla rangella
//        //jne
//        
//        return 0;
//    }
    
    public void start(){
        running=true;
        while(running){
            world.evolve();
            //piirrä?
        }
        
    }
    
    // vain testejä varten
    public void setRunning(boolean state){
        running=state;
    }
    
    public void stop(){
        running=false;
    }
    
    public void save(String filename){
        Saver saver = new Saver(filename, this);
        saver.save();
    }
    
    public void load(String filename){
        Loader loader = new Loader(filename);
        Session loaded = loader.load();
        
        this.map = loaded.map;
        this.prioritys = loaded.prioritys;
        this.rules = loaded.rules;
        this.running = loaded.running;
        this.world = loaded.world;
    }
    
    
}

