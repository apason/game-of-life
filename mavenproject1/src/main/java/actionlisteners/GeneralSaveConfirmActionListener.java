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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author apa
 */
public class GeneralSaveConfirmActionListener implements ActionListener {

    private JFrame frame;
    private GUI gui;
    private JTextField size;

    public GeneralSaveConfirmActionListener(GUI gui, JFrame frame, JTextField size) {
        this.gui = gui;
        this.frame = frame;
        this.size = size;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        gui.getSession().createWorld(Integer.parseInt(size.getText()));
        frame.setVisible(false);

    }
}
