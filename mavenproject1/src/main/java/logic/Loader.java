package logic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import interfaces.*;

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
        Session loaded = (Session) data.readObject();
        data.close();
        
        return loaded;
    }
}

