package logic;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import interfaces.*;

/**
 * Simulaation tallennuksen mahdollistava luokka.
 * @author apa
 */
public class Saver {
    private String filename;
    private Session session;
    
    
    /**
     * @param filename tiedosto johon Session tallennetaan
     * @param session tallennettava Session
     */
    public Saver (String filename, Session session){
        this.filename=filename;
        this.session=session;
    }
    
    /**
     * Tallentaa konstruktorissa määriteltyyn tiedostoon konstruktorissa määritellyn session
     * 
     * @throws Exception 
     */
    public void save() throws Exception{
        
        FileOutputStream out = new FileOutputStream(filename);
        ObjectOutputStream data = new ObjectOutputStream(out);
        data.writeObject(session);
        data.close();
    }
}


