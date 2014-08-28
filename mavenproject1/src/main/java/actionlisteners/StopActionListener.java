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
public class StopActionListener implements ActionListener{

    GUI gui;
    
    public StopActionListener(GUI gui){
        this.gui=gui;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        gui.getSession().stop();
    }
    
}
