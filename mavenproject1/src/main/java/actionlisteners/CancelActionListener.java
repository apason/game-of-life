/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author apa
 */
public class CancelActionListener implements ActionListener{
    
    private JFrame frame;
    private Integer d;
    
    public CancelActionListener(JFrame frame, Integer d){
        this.frame=frame;
        this.d=d;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        d=1;
        frame.setVisible(false);
    }
    
}
