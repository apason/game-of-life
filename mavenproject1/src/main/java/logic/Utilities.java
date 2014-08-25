/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author apa
 */
public class Utilities {
    
    //tyhmä metodi. menee rikki jos filenamessa ".dat" jossain muualla kuin
    //lopussa tai jos filenamena esim "x.data"
    public static String correctFilename(String filename){
        if(filename.contains(".dat"))
            return filename;
        return filename + ".dat";
    }
    
    public static boolean correctConditionList(String s){
        return s.matches("([0-9],|[1-9][0-9],)*([0-9]|[1-9][0-9])");
    }
    
    public static boolean correctSize(String s){
        return s.matches("[2-9]|[1-9][0-9]|[1-9][0-9][0-9]");
    }
    
    public static boolean correctSteptime(String s){
        return s.matches("[1-9]|[1-9][0-9]|[1-9][0-9][0-9]|1?([0-9]|[0-9][0-9]|[0-9][0-9][0-9])");
    }
    
    public static boolean correctIterations(String s){
        return s.matches("[1-9]|[1-9][0-9]|[1-9][0-9][0-9]");
    }
    
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
