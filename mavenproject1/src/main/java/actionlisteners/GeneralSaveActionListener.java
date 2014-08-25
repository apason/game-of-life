/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actionlisteners;

import interfaces.GUI;
import interfaces.Gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logic.Utilities;

/**
 *
 * @author apa
 */
public class GeneralSaveActionListener implements ActionListener{
    
    GUI gui;
    JTextField size;
    JTextField steptime;
    JTextField iterations;
    
    
    
    public GeneralSaveActionListener(GUI gui,JTextField size, JTextField steptime, JTextField iterations) {
        this.gui=gui;
        this.size=size;
        this.steptime=steptime;
        this.iterations=iterations;
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Integer d = 0;
        if(!Utilities.correctSize(size.getText())){
            size.setText("Error: Size must be integer [2,999]");
            return;
        }
        if(gui.getSession().getWorld()!=null && gui.getSession().getWorld().getMap().length!=Integer.parseInt(size.getText())){
            //varoitus ylikirjoituksesta
            JFrame frame = new JFrame("Owerwrite?");
            JLabel label = new JLabel();
            JButton ok = new JButton();
            JButton cancel = new JButton();
            label.setText("To change the size you will lost current world!");
            ok.setText("Ok");
            cancel.setText("Cancel");
            
            ok.addActionListener(new WindowCloseActionListener(frame));
            cancel.addActionListener(new CancelActionListener(frame, d));
            
            frame.getContentPane().add(label, BorderLayout.NORTH);
            frame.getContentPane().add(ok, BorderLayout.WEST);
            frame.getContentPane().add(cancel, BorderLayout.EAST);
            frame.pack();
            frame.setVisible(true);
            
        }
        if(d!=0)
            return;
        gui.getSession().createWorld(Integer.parseInt(size.getText()));
        if(!Utilities.correctIterations(iterations.getText())){
            iterations.setText("Error: Iterations must be integer [1,999]");
            return;
        }
        gui.setIterationsPerStep(Integer.parseInt(iterations.getText()));
        if(!Utilities.correctSteptime(steptime.getText())){
            steptime.setText("Error: Steptime must be integer [1,1999]");
            return;
        }
        gui.setTimePerStep(Integer.parseInt(steptime.getText()));
        
    }
    
}