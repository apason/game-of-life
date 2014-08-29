package interfaces;

import filehandling.Loader;
import filehandling.Saver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import logic.*;

/**
 * Luokka sisältää kaiken tallennukseen/lataamisen tarvittavan datan ja
 * toimii osana käyttöliittymää
 * @author apa
 */
public class Session implements Runnable{

    /** Sisältää tiedon onko start() metodi suorituksessa */
    private boolean running;
    /** Sessioniin liittyvä World olio. Suurin osa (esim. evolve()) simulaatiosta tapahtuu tämän olion metodeilla */
    private World world;
    /** Tieto kaikista eri solutyypeistä/"lajeista", annetaan parametriksi Worldin konstruktorille. */
    private ArrayList<Rules> rules;
    /** Sessionin tunnettava gui koska rajapinnan Runnable metodi
     *  Run tarvitsee tämän tiedon eikä sille voi antaa parametrejä. */
    private GUI gui;

    /**
     * Konstruktori
     */
    public Session(GUI gui) {
        running = false;
        rules = new ArrayList<Rules>();
        this.gui=gui;
    }

    public boolean getRunning() {
        return running;
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<Rules> getRules() {
        return rules;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public void setRules(ArrayList<Rules> rules){
        this.rules=rules;
    }

    /**
     * Luo ja alustaa World olion
     * @param size maailmalle annettavan solumäärän neliöjuuri
     */
    public void createWorld(int size) {
        world = new World(size, rules);
    }

    /**
     * Lisää rules listaan uuden Rules olion (mikäli se ei ole ristiriidassa
     * itsensä tai muiden kanssa)
     * @param rule lisättävä sääntö
     * @throws RuntimeException jos lisättävä Rules ei ole validi
     */
    public void addRule(Rules rule) throws Exception {

        for (int i : rule.getBirth()) {
            if (rule.getDie().contains(i)) {
                throw (new RuntimeException("conflicts in death and birth conditions"));
            }
        }

        for (Rules r : rules) {
            if (r.getPriority() == rule.getPriority()) {
                throw (new RuntimeException("priority must be unique"));
            }
        }

        rules.add(rule);
        Collections.sort(rules);

    }

    /**
     * Poistaa rules listalta annetun Rules olion
     * @param rule poistettava sääntö
     */
    public void removeRule(Rules rule) {
        rules.remove(rule);
    }

    /**
     * Käynnistää simulaation
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            for(int i=0;i<gui.getIterationsPerStep(); i++)
                world.evolve();
            try{
            TimeUnit.MILLISECONDS.sleep(gui.getTimePerStep());
            }catch(Exception e){
                System.out.println("wait ei toiminut");;
            }
            gui.createCells();
            gui.getFrame().pack();
        }

    }

    // vain testejä varten
    public void setRunning(boolean state) {
        running = state;
    }

    /**
     * Asettaa simulaation jatkumista edellyttävän running muuttujan.
     */
    public void stop() {
        running = false;
    }

    /**
     * Tallentaa senhetkisen simulaation tiedostoon
     * @param filename tiedosto johon tallennus tapahtuu
     */
    public void save(String filename) {
        Saver saver = new Saver(filename, this);
        try {
            saver.save();
        } catch (Exception e) {
            System.out.println("Error: cannot write to file");
            e.printStackTrace();
        }
    }

    /**
     * Lataa vanhan simulaation tiedostosta
     * @param filename tiedosto josta lataus tapahtuu
     */
    public void load(String filename) {
        Loader loader = new Loader(filename);
        Session loaded;
        try {
            loaded = loader.load();
            this.rules=loaded.rules;
            this.world = loaded.world;
            running=false;
        } catch (Exception e) {
            System.out.println("Error: cannot read from file");
            e.printStackTrace();
        }

    }
    
    /**
     * Vain testejä varten. Ei voi vertailla kahta mielivaltaista Session oliota!
     * @param o mihin verrataan
     * @return true jos kyseessä sama olio, muuten false
     */
    @Override
    public boolean equals(Object o){
        Session s;
        if(o == null)
            return false;
        if(o.getClass()!=this.getClass())
            return false;
        s=(Session) o;
        if ((s.getWorld()==null && this.getWorld()!=null) || (s.getRules() == null && this.getRules()!=null))
            return false;
        if((s.getRules()==null&&this.getRules()!=null))
            return false;
        if(this.getRules()!=null)
            if(this.getRules().size()!= s.getRules().size())
                return false;
        if(this.getWorld()!=null){
            if(this.getWorld().getMap().length!=s.getWorld().getMap().length)
                return false;
        }
        return true;
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.rules.size();
        hash = 37 * hash + this.world.getMap().length;
        return hash;
    }
    
}
