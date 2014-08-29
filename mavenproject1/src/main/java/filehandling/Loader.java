package filehandling;

import interfaces.Session;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import logic.Rules;
import logic.World;


/**
 * Mahdollistaa aiemmin pelatun pelin tilan lataamisen tiedostosta
 * @author apa
 */
public class Loader {
    
    /** Määrittelee tiedoston josta luku tapahtuu */
    private String filename;
    
    /**
     * Konstruktori ottaa parametrinä tiedoston josta luku tapahtuu.
     * @param filename tiedosto josta Session luetaan
     */
    public Loader (String filename){
        this.filename=filename;
    }
    

    /**
     * Lataa session konstrukrotille määritellystä tiedostosta.
     * @return tiedostosta luettu Session
     * @throws Exception
     */
    public Session load() throws Exception{
        FileInputStream in = new FileInputStream(filename);
        ObjectInputStream data = new ObjectInputStream(in);
        Session loaded = new Session(null);
        World world = (World) data.readObject();
        ArrayList<Rules> rules = (ArrayList<Rules>) data.readObject();
        data.close();
        
        loaded.setWorld(world);
        loaded.setRules(rules);
        
        return loaded;
    }
}

