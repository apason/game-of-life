/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionlisteners;

import interfaces.GUI;
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

    private int edit;
    private GUI gui;

    public EditActionListener(GUI gui, int edit) {
        this.edit=edit;
        this.gui=gui;
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

    @Override
    public void actionPerformed(ActionEvent ae) {

        String s = getSelectedButtonText(gui.getRulesGroup());
        Rules del = null;
        
        if(s==null)
            return;
        
        String[] st = s.split(" ");
        int d = Integer.parseInt(st[1]);
        for (Rules r : gui.getSession().getRules()) {
            if (r.getPriority() == d) {
                del = r;
                break;
            }
        }

        
        gui.getSession().getRules().remove(del);
        gui.createOptionsComponents();
        if (edit==1&&del!=null) {
            gui.getBl().setText(Utilities.listToString(del.getBirth()));
            gui.getDl().setText(Utilities.listToString(del.getDie()));
            gui.getPriority().setText(del.getPriority() + "");
            gui.getColor().setBackground(gui.getColorMap().get(del.getPriority()));
        }
        gui.getColorMap().remove(del.getPriority());
        gui.getOptionsWindow().pack();
        gui.getOptionsWindow().setVisible(true);
    }

}
