/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ohjelman käyttämät staattiset metodit jotka eivät sovi muihin luokkiin.
 * Tästä Luokasta ei ole syytä tehdä ilmentymää, sillä kaikki metodit ovat staattisia.
 * @author apa
 */
public class Utilities {
    /**
     * Metodi joka lisää tiedostonimen loppuun ".dat" jos se ei siellä jo ole.
     * Metodi ei kuitenkaan tee mitään, jos tiedoston nimessä on ".dat" jossain muualla
     * Kuin lopussa. Esim. "file.dat.tiedosto" nimelle ei tule lisäystä ".dat". Tämä
     * ei kuitenkaan haittaa. 
     * @param filename Tiedostonimi, joka mahdollisesti muutetaan
     * @return Mahdollisesti muutettu- tai parametrina saatu tiedostonimi
     */
    public static String correctFilename(String filename){
        if(filename.contains(".dat"))
            return filename;
        return filename + ".dat";
    }
    
    /**
     * Tarkistaa että death/birth -conditionit ovat oikeassa formaatissa lukujen parsimista varten.
     * @param s Tarkistettava parametrilista
     * @return True jos lista on oikeassa muodossa ja luvut väliltä [0,99], muuten false
     */
    public static boolean correctConditionList(String s){
        return s.matches("([0-9],|[1-9][0-9],)*([0-9]|[1-9][0-9])");
    }
    
    /**
     * Tarkistaa että annettu merkkijono vastaa sallittua kokoa.
     * @param s Tarkistettava tekstikenttä
     * @return True jos tekstikentän sisältö vastaa lukua [2,1999], muuten false.
     */
    public static boolean correctSize(String s){
        return s.matches("[2-9]|[1-9][0-9]|[1-9][0-9][0-9]");
    }
    
    /**
     * Tarkistaa että annettu merkkijono vastaa validia steptimeä.
     * @param s Tarkistettava tekstikenttä
     * @return True jos tekstikentän sisältö vastaa lukua [1,1999], muuten false
     */
    public static boolean correctSteptime(String s){
        return s.matches("[1-9]|[1-9][0-9]|[1-9][0-9][0-9]|1?([0-9]|[0-9][0-9]|[0-9][0-9][0-9])");
    }
    
    /**
     * Tarkistaa että annettu merkkijon vastaa validia iteraatioaikaa.
     * @param s Tarkistettava tekstikenttä
     * @return True jos tekstikentän sisältö vastaa lukua [1,999], muuten false
     */
    public static boolean correctIterations(String s){
        return s.matches("[1-9]|[1-9][0-9]|[1-9][0-9][0-9]");
    }
    
    /**
     * Muuntaa kokonaislukuja sisältävän arraylistin metodin correctConditionList hyväksymään muotoon
     * @param list Lista josta paluuarvo kootaan
     * @return Muotoa x,y,z... oleva merkkijono, jossa x,y,z € int
     */
    public static String listToString(ArrayList<Integer> list){
        StringBuilder ret = new StringBuilder();
        Iterator<Integer> I = list.iterator();
        while(I.hasNext()){
            ret.append(I.next());
            if(I.hasNext())
                ret.append(",");
        }
        return ret.toString();  
    }
    
}
