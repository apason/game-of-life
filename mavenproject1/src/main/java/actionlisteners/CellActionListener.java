/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import interfaces.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Rules;

/**
 *
 * @author apa
 */
public class CellActionListener implements ActionListener {

    private GUI gui;
    private int i;
    private int j;
    private Rules rules;

    public CellActionListener(GUI gui, int i, int j, Rules rules) {
        this.gui = gui;
        this.i = i;
        this.j = j;
        this.rules = rules;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!gui.getSession().getRunning()) {
            gui.getSession().getWorld().getMap()[i][j].setRules(rules);
            gui.createComponents(gui.getFrame().getContentPane());
            gui.getFrame().pack();
            gui.getFrame().setVisible(true);
        }
    }

}
