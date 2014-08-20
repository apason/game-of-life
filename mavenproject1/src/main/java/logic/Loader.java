package logic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Mahdollistaa aiemmin pelatun pelin tilan lataamisen tiedostosta
 * @author apa
 */

import interfaces.*;

public class Loader {
    private String filename;
    
    /**
     * 
     * @param filename tiedosto josta Session luetaan
     */
    public Loader (String filename){
        this.filename=filename;
    }
    

    /**
     * Lataa session konstrukrotille määritellystä tiedostosta
     * @return tiedostosta luettu Session
     * @throws Exception
     */
    public Session load() throws Exception{
        FileInputStream in = new FileInputStream(filename);
        ObjectInputStream data = new ObjectInputStream(in);
        Session loaded = (Session) data.readObject();
        data.close();
        
        return loaded;
    }
}

