/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actionlisteners;

import interfaces.Session;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logic.Rules;

/**
 *
 * @author apa
 */
public class AddRule implements ActionListener{
    
    Session session;
    private JTextField bc;
    private JTextField dc;
    private JTextField priority;

    public AddRule(Session session, JTextField bc, JTextField dc, JTextField priority){
        this.session=session;
        this.bc=bc;
        this.dc=dc;
        this.priority=priority;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        ArrayList<Integer> bl = new ArrayList<Integer>();
        ArrayList<Integer> dl = new ArrayList<Integer>();
        Rules rule;
        int prior = Integer.parseInt(priority.getText());
        String[] tmp = bc.getText().split(",");
        for(String s : tmp)
            dl.add(Integer.parseInt(s));
        tmp = dc.getText().split(",");
        for(String s : tmp)
            dl.add(Integer.parseInt(s));
        bc.setText("");
        dc.setText("");
        priority.setText("");
        rule=new Rules(bl,dl,prior);
        try{
            session.addRule(rule);
        }catch (Exception e){
            JFrame frame = new JFrame("Error");
            JLabel label = new JLabel();
            JButton button = new JButton();
            //frame.setPreferredSize(new Dimension(40, 20));
            label.setText("Unable to add rule:\n" + e.toString());
            button.setText("Ok");
            
            button.addActionListener(new ButtonActionListener(frame));
            
            frame.getContentPane().add(label, BorderLayout.NORTH);
            frame.getContentPane().add(button);
            frame.pack();
            frame.setVisible(true);
            
        }
    }  
}

class ButtonActionListener implements ActionListener {

    private JFrame frame;
    
    public ButtonActionListener(JFrame frame){
        this.frame=frame;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        frame.setVisible(false);
    }
    
}