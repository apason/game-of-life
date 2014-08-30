/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import interfaces.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author apa
 */
public class WindowCloseActionListener implements ActionListener {

    private int redrawmainwindow;
    private JFrame frame;
    private GUI gui;

    public WindowCloseActionListener(GUI gui, JFrame frame, int redrawmainwindow) {
        this.gui = gui;
        this.frame = frame;
        this.redrawmainwindow = redrawmainwindow;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        gui.getSession().getWorld().removeExtras();

        if (redrawmainwindow != 0) {
            gui.createComponents(gui.getFrame().getContentPane());
            gui.getFrame().pack();
            gui.getFrame().setVisible(true);
        }

        frame.setVisible(false);

    }

}
