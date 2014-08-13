/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

/**
 *
 * @author apa
 */

import interfaces.*;

public class Saver {
    private String filename;
    private Session session;
    
    public Saver (String filename, Session session){
        this.filename=filename;
        this.session=session;
    }
    public void save(){
        
    }
}
