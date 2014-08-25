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
import javax.swing.JTextField;
import logic.Rules;
import logic.Utilities;

/**
 *
 * @author apa
 */
public class EditActionListener implements ActionListener{

    private JTextField bl;
    private JTextField dl;
    private JTextField priority;
    private JRadioButton button;
    private ArrayList<Rules> rules;
    
    public EditActionListener(ButtonModel button, JTextField bl, JTextField dl, JTextField priority,ArrayList<Rules> rules){
        this.button=(JRadioButton) button; 
        this.bl=bl;
        this.dl=dl;
        this.priority=priority;
        this.rules=rules;
    }
    
    //oletetaan että kaikki toimii ja rulesissa tosiaan on buttonin prioriteettiä vastaava solutyyppi
    @Override
    public void actionPerformed(ActionEvent ae) {
        Rules del=null;
        String[] s = button.getText().split(" ");
        int d = Integer.parseInt(s[1]);
        for(Rules r: rules){
            if(r.getPriority()==d){
                del=r;
                break;
            }
        }
        //luokan voisi yhdistää luokan removeactionlistenerin kanssa esim parametrilla boolean edit
        //siten että seuraavat lauseet suoritetaan vain jos edit käytössä.
        bl.setText(Utilities.listToString(del.getBirth()));
        dl.setText(Utilities.listToString(del.getDie()));
        priority.setText(del.getPriority()+ "");
    }
    
}
