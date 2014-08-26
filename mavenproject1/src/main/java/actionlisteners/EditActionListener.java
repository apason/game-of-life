/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import logic.Rules;
import logic.Utilities;

/**
 *
 * @author apa
 */
public class EditActionListener implements ActionListener {

    private JTextField bl;
    private JTextField dl;
    private JTextField priority;
    private ArrayList<Rules> rules;
    private ButtonGroup rulesgroup;
    private int edit;

    public EditActionListener(ButtonGroup rulesgroup, JTextField bl, JTextField dl, JTextField priority, ArrayList<Rules> rules, int edit) {
        this.rulesgroup = rulesgroup;
        this.bl = bl;
        this.dl = dl;
        this.priority = priority;
        this.rules = rules;
        this.edit=edit;
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    //oletetaan että kaikki toimii ja rulesissa tosiaan on buttonin prioriteettiä vastaava solutyyppi
    @Override
    public void actionPerformed(ActionEvent ae) {

        String s = getSelectedButtonText(rulesgroup);
        Rules del = null;
        
        String[] st = s.split(" ");
        int d = Integer.parseInt(st[1]);
        for (Rules r : rules) {
            if (r.getPriority() == d) {
                del = r;
                break;
            }
        }

        if (edit==1&&del!=null) {
            bl.setText(Utilities.listToString(del.getBirth()));
            dl.setText(Utilities.listToString(del.getDie()));
            priority.setText(del.getPriority() + "");
        }
        rules.remove(del);
    }

}
