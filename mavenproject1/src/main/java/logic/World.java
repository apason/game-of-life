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
public class World {
    private Cell[][] world;
    private Rules[] rules;
    
    public World(int size,Rules[] rules){
        this.world=new Cell[size][size];
        this.rules=rules;
    }
    public void setCell(int x, int y,Cell cell){
        world[y][x]=cell;
    }
    
    public Cell[][] getWorld(){
        return this.world;
    }
    
    public Rules[] getRules(){
        return this.rules;
    }
    
    public void evolve(){
        Cell[][] tmp = new Cell[this.world.length][this.world.length];
        for(int i=0;i<world.length;i++)
            for(int j=0;j<world.length;j++)
                tmp[i][j]=world[i][j].copy();
        
        for(int i=0;i<world.length;i++)
            for(int j=0;j<world.length;j++){
                int nearbys=countNearbys(i,j);
                if(world[i][j].getRules()!=null && nearbys<world[i][j].getRules().getDie())
                    tmp[i][j].setRules(null);
                else{
                    int biggestpriority=getBiggestPriority(i,j);
                    for(int k = biggestpriority-1; k>=0; k--){
                        if(rules[k].getBirth()<=nearbys/*&&rules[k].getDie()>nearbys*/){
                            tmp[i][j].setRules(rules[k]);
                            break;
                        }
                    }
                }
                
            }
        world=tmp;
    }
        
    public int countNearbys(int i, int j){
        int nearbys=0;
        for(int y=i-1;y<=i+1;y++)
            for(int x=j-1;x<=j+1;x++){
                if((y==i&&x==j)||y<0||y>=world.length||x<0||x>=world.length||world[y][x].getRules()==null)
                    continue;
                nearbys+=world[y][x].getRules().getPriority();
            }
        return nearbys;
    }
        
    public int getBiggestPriority(int i, int j){
        int biggest=0;
        for(int y=i-1;y<=i+1;y++)
            for(int x=j-1;x<=j+1;x++){
                if((y==i&&x==j)||y<0||y>=world.length||x<0||x>=world.length||world[y][x].getRules()==null)
                    continue;
                int priority = world[y][x].getRules().getPriority();
                biggest=priority>biggest?priority:biggest;
            }
        return biggest;
    }
    public void printWorld(){
        for(int i=0;i<world.length;i++){
            for(int j=0;j<world.length;j++){
                if(world[i][j].getRules()== null)
                    System.out.print("0, ");
                else
                    System.out.print(world[i][j].getRules().getPriority() + ", ");
            }
            System.out.println("");
        }
    }
        
        
        
        
}
