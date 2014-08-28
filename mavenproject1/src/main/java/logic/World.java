package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.Serializable;

/**
 * Sisältää solutaulukon ja tiedot solujen tyypeistä sekä solutaulukkoon ja sen kehitykseen liittyvät metodit.
 * @author apa
 */
public class World implements Serializable {
    /** Simuloitava maailma. Taulukollinen soluja jotka ovat joko kuolleita tai edustavat tiettyä "lajia" */
    private Cell[][] map;
    /** Kaikki mahdolliset "lajit" sisältävä attribuutti. */
    private ArrayList<Rules> rules;
    
    /**
     * @param size Määrittää Worldin sisältävän solutaulukon koon
     * @param rules Sisältää kaikki mahdolliset Worlissa olevien solujen tyypiy (Rules:t)
     */
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
    
    /**
     * Palauttaa kopion Worldin Cell taulukosta
     * @return samantilainen Cell taulukko kuin ko. Worldilla
     */
    public Cell[][] cloneMap(){
        Cell[][] clone = new Cell[this.map.length][this.map.length];
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++)
                clone[i][j]=map[i][j].copy();
        return clone;
    }
    
    /**
     * Ohjelman kannalta oleellisin metodi.
     * Suurin osa ohjelman suorituksesta vietetään tässä metodissa.
     * Hoitaa solukartan päivitystä.
     */
    public void evolve(){
        Cell[][] tmp = cloneMap();
        
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++){
                eliminate(i,j,tmp);
                giveBirth(i,j,tmp);
            }
        
        map=tmp;
    }

    /**
     * Tappaa solun jos säännöt edellyttävät
     * @param y solukartan pystykoordinaatti
     * @param x solukartan vaakakoordinaatti
     * @param generation2 Cell[][] taulukkoa vastaava seuraava sukupolvi (laskuvaiheessa)
     */
    public void eliminate(int y, int x, Cell[][] generation2){
        int nearbys=countNearbys(y,x);
        if(map[y][x].getRules()!=null && map[y][x].getRules().getDie().contains(nearbys))
            generation2[y][x].setRules(null);
    }
    
    /**
     * Synnyttää solun jos säännöt edellyttävät
     * @param y solun pystykoordinaatti
     * @param x solun vaakakoordinaatti
     * @param generation2 Cell[][] taulukkoa vastaava seuraava sukupolvi (laskuvaiheessa)
     */
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
        
    /**
     * Laskee parametreina annetun solun naapurisolujen prioriteettien summan
     * @param i solun pystykoordinaatti
     * @param j solun vaakakoordinaatti
     * @return naapurisolujen prioriteettien summa
     */
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
    
    /**
     * Ylöskirjaa kaikkien naapurisolujen prioriteetit
     * @param i solun pystykoordinaatti
     * @param j solun vaakakoordinaatti
     * @return  kaikkien naapurisolujen prioriteetit
     */
    public ArrayList<Integer> getPrioritys(int i, int j){
        ArrayList<Integer> prioritys = new ArrayList<Integer>();
        for(int y=i-1;y<=i+1;y++)
            for(int x=j-1;x<=j+1;x++){
                if((y==i&&x==j)||y<0||y>=map.length||x<0||x>=map.length||map[y][x].getRules()==null)
                    continue;
                prioritys.add(map[y][x].getRules().getPriority());
            }
        return prioritys;
    }
    
    /**
     * Tulostaa maailman. Metodi ei käytössä graafisessa käyttöliittymässä.
     */
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
    
    /**
     * Alustaa maailman: luo solut ja merkitsee jokaisen solun kuolleeksi (Rules = null)
     */
    public void initializeMap(){
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++)
                map[i][j]=new Cell(null);
    }
    
    /**
     * Arpoo jokaiselle solulle jonkun rules listalla olevan tyypin tai arvon null.
     */
    public void randomizeMap(){
        Random random = new Random();
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map.length;j++){
                int x = random.nextInt()%(rules.size()+1);
                if(x>0)
                    map[i][j].setRules(rules.get(x-1));
                else
                    map[i][j].setRules(null);
            }
    }
}

