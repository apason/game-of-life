package interfaces;

/**
 * Sisältää ne tiedot mitä tarvitaan tallennukseen.
 * Toimii käyttöliittymänä guille.
 * @author apa
 */

import java.io.Serializable;

import java.util.ArrayList;
import logic.*;

public class Session implements Serializable {
    
    private boolean running;
    private World world;
    private ArrayList<Integer> prioritys;
    private Cell[][] map; //tarviiko tätä?
    private ArrayList<Rules> rules;
    
    /**
     * Konstruktori
     */
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
    
    public void setWorld(World world){
        this.world=world;
    }
    
    /**
     * Luo ja alustaa World olion
     * @param size maailmalle annettavan solumäärän neliöjuuri
     */
    public void createWorld(int size){
        //tutki että rules on ok
        world=new World(size, rules);
        world.initializeMap();
        map=world.getMap();
    }
    
    /**
     * Lisää rules listaan uuden Rules olion (mikäli se ei ole ristiriidassa itsensä tai muiden kanssa)
     * @param rule lisättävä sääntö
     * @throws Exception e
     */
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
    
    /**
     * Poistaa rules listalta annetun Rules olion
     * @param rule poistettava sääntö
     */
    public void removeRule(Rules rule){
        rules.remove(rule);
    }
    
//    public int checkSessionIntegrity(){
//        //mappi alustettu, prioriteetit tiedossa, ja kaikki erilaisia, prioriteetit oikealla rangella
//        //jne
//        
//        return 0;
//    }
    
    /**
     * Käynnistää simulaation
     */
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
    
    /**
     * Asettaa simulaation jatkumista edellyttävän running muuttujan.
     */
    public void stop(){
        running=false;
    }
    
    
    /**
     * Tallentaa senhetkisen simulaation tiedostoon
     * @param filename tiedosto johon tallennus tapahtuu
     */
    public void save(String filename){
        Saver saver = new Saver(filename, this);
        try{
            saver.save();
        } catch(Exception e){
            System.out.println("Error: cannot write to file");
            e.printStackTrace();
        }
    }
    
    /**
     * Lataa vanhan simulaation tiedostosta
     * @param filename tiedosto josta lataus tapahtuu
     */
    public void load(String filename){
        Loader loader = new Loader(filename);
        Session loaded;
        try{
            loaded = loader.load();
        }catch(Exception e){
            System.out.println("Error: cannot read from file");
            e.printStackTrace();
            return;
        }
        
        this.map = loaded.map;
        this.prioritys = loaded.prioritys;
        this.rules = loaded.rules;
        this.running = loaded.running;
        this.world = loaded.world;
    }
    
    
}

