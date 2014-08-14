/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author apa
 */

public class World {
    private Cell[][] map;
    private ArrayList<Rules> rules;
    
    public World(int size,ArrayList<Rules> rules){
        this.map=new Cell[size][size];
        this.rules=rules;
        initializeMap();
    }
    public void setCell(int x, int y,Cell cell){
        map[y][x]=cell;
    }
    
    public Cell[][] getMap(){
        return this.map;
    }
    
    public ArrayList<Rules> getRules(){
        return this.rules;
    }
    
    public Cell[][] cloneMap(){
        Cell[][] clone = new Cell[this.map.length][this.map.length];
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++)
                clone[i][j]=map[i][j].copy();
        return clone;
    }
    
    public void evolve(){
        Cell[][] tmp = cloneMap();
        
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++){
                //downgrade??
                eliminate(i,j,tmp);
                giveBirth(i,j,tmp);
            }
        
        map=tmp;
    }
    
    //tappaa solun JOS säännöt edellyttävät
    public void eliminate(int y, int x, Cell[][] generation2){
        int nearbys=countNearbys(y,x);
        if(map[y][x].getRules()!=null && map[y][x].getRules().getDie().contains(nearbys))
            generation2[y][x].setRules(null);
    }
    
    //synnyttää solun JOS säännöt edellyttävät
    public void giveBirth(int y, int x, Cell[][] generation2){
        ArrayList<Integer> prioritys = getPrioritys(y,x);
        int nearbys=countNearbys(y,x);
        
        //olettaen että rules on järjestetty laskevan prioriteetin mukaan
        for(Rules rule : rules){
            if(prioritys.contains(rule.getPriority()) && rule.getBirth().contains(nearbys)){
                generation2[y][x].setRules(rule);
                break;
            }
        }
    }
        
    public int countNearbys(int i, int j){
        int nearbys=0;
        for(int y=i-1;y<=i+1;y++)
            for(int x=j-1;x<=j+1;x++){
                if((y==i&&x==j)||y<0||y>=map.length||x<0||x>=map.length||map[y][x].getRules()==null)
                    continue;
                nearbys+=map[y][x].getRules().getPriority();
            }
        return nearbys;
    }
    
    public ArrayList<Integer> getPrioritys(int i, int j){
        ArrayList<Integer> prioritys = new ArrayList<Integer>();
        for(int y=i-1;y<=i+1;y++)
            for(int x=j-1;x<=j+1;x++){
                if((y==i&&x==j)||y<0||y>=map.length||x<0||x>=map.length||map[y][x].getRules()==null)
                    continue;
                //setti vois olla parempi tähän
                prioritys.add(map[y][x].getRules().getPriority());
            }
        return prioritys;
    }
    
//    //turha metodi
//    public int getBiggestPriority(int i, int j){
//        int biggest=0;
//        for(int y=i-1;y<=i+1;y++)
//            for(int x=j-1;x<=j+1;x++){
//                if((y==i&&x==j)||y<0||y>=map.length||x<0||x>=map.length||map[y][x].getRules()==null)
//                    continue;
//                int priority = map[y][x].getRules().getPriority();
//                biggest=priority>biggest?priority:biggest;
//            }
//        return biggest;
//    }
    
    public void printWorld(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                if(map[i][j].getRules()== null)
                    System.out.print("0, ");
                else
                    System.out.print(map[i][j].getRules().getPriority() + ", ");
            }
            System.out.println("");
        }
    }
    
    public void initializeMap(){
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++)
                map[i][j]=new Cell(null);
    }
    
    public void randomizeMap(){
        Random random = new Random();
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++){
                int x = random.nextInt()%(rules.size()+1);
                if(x>0)
                    map[i][j].setRules(rules.get(x-1));
            }
    }
}
