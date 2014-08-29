/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actionlisteners;

import interfaces.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author apa
 */
public class RandomizeActionListener implements ActionListener {

    private GUI gui;
    
    public RandomizeActionListener(GUI gui){
        this.gui=gui;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        gui.getSession().stop();
        gui.getSession().getWorld().randomizeMap();
        gui.createComponents(gui.getFrame().getContentPane());
        gui.getFrame().pack();
        gui.getFrame().setVisible(true);
                
    }
    
}
