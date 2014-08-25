/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import logic.Rules;

/**
 *
 * @author apa
 */
public class RemoveActionListener implements ActionListener{
    
    private JRadioButton button;
    private ArrayList<Rules> rules;
    
    public RemoveActionListener(ButtonModel button, ArrayList<Rules> rules){
        this.button = (JRadioButton) button;
        this.rules=rules;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Rules del=null;
        String[] s = button.getText().split(" ");
        int d = Integer.parseInt(s[1]);
        for(Rules r : rules)
            if(r.getPriority()==d){
                del=r;
                break;
            }
        rules.remove(del);
    }
}
