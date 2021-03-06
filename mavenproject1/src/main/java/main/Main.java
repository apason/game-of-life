package main;

import interfaces.*;
import javax.swing.SwingUtilities;

/**
 * Main luokka josta ohjelma käynnistetään.
 * @author apa
 */
public class Main {

    /**
     * Ohjelman käynnistys
     * @param args ohjelma ei käsittele sille annettuja argumenttejä
     */
    public static void main(String[] args) {
            GUI gui = new GUI();
            SwingUtilities.invokeLater(gui);
    }

}
