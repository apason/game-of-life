/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actionlisteners;

import interfaces.GUI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import logic.Rules;
import logic.Utilities;

/**
 *
 * @author apa
 */
public class AddRule implements ActionListener{
    
    private GUI gui;

    public AddRule(GUI gui){
        this.gui=gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(!Utilities.correctConditionList(gui.getBl().getText())){
            gui.getBl().setText("Syntax error: list conditions (integers) separated with comma.");
            return;
        }
        if(!Utilities.correctConditionList(gui.getDl().getText())){
            gui.getDl().setText("Syntax error: list conditions (integers) separated with comma.");
            return;
        }
        
        ArrayList<Integer> bl = new ArrayList<Integer>();
        ArrayList<Integer> dl = new ArrayList<Integer>();
        Rules rule;
        int prior = Integer.parseInt(gui.getPriority().getText());
        String[] tmp = gui.getBl().getText().split(",");
        for(String s : tmp)
            bl.add(Integer.parseInt(s));
        tmp = gui.getDl().getText().split(",");
        for(String s : tmp)
            dl.add(Integer.parseInt(s));
        gui.getBl().setText("");
        gui.getDl().setText("");
        gui.getPriority().setText("");
        rule=new Rules(bl,dl,prior);
        try{
            gui.getSession().addRule(rule);
            gui.getColorMap().put(prior, gui.getColor().getBackground());
            gui.createOptionsComponents();
            gui.getOptionsWindow().pack();
            gui.getOptionsWindow().setVisible(true);
        }catch (Exception e){
            JFrame frame = new JFrame("Error");
            JLabel label = new JLabel();
            JButton button = new JButton();
            label.setText("Unable to add rule:\n" + e.toString());
            button.setText("Ok");
            
            button.addActionListener(new WindowCloseActionListener(gui,frame,0));
            
            frame.getContentPane().add(label, BorderLayout.NORTH);
            frame.getContentPane().add(button);
            frame.pack();
            frame.setVisible(true);
            
        }
    }  
}

