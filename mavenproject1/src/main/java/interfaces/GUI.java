/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import actionlisteners.AddRule;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import logic.Rules;
import logic.Utilities;

/**
 *
 * @author apa
 */
public class GUI implements Runnable {

    private Session session;
    private String filename;

    private JFrame frame;
    //framen itemit
    private JMenuBar menu;
    private JPanel panel;
    //menun itemit
    private JMenu file;
    private JMenu edit;
    //file menun itemit
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem saveas;
    private JMenuItem exit;
    //edit menun itemit
    private JMenuItem options;
    //panelin itemit
    JButton[][] table;
    //muut itemit
    private JFileChooser filechooser;
    private JFrame optionswindow;
    //private JTabbedPane optionspane;
    

    public GUI() {
        session = new Session();
    }

    @Override
    public void run() {
        frame = new JFrame("Apa's cellular automata");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        //framen itemit
        menu = new JMenuBar();
        panel = new JPanel();
        //menun itemit
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //file menun itemit
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveas = new JMenuItem("Save As...");
        exit = new JMenuItem("Exit");
        //edit menun itemit
        options = new JMenuItem("Options");
        //panelin itemit
        if(session.getWorld()!=null && session.getWorld().getMap()!=null){
            int size = session.getWorld().getMap().length;
            int priority;
            table = new JButton[size][size];
            panel.setLayout(new GridLayout(size,size));
            for(int i=0;i<size;i++)
                for(int j=0;j<size;j++){
                    table[i][j]=new JButton();
                    try{
                        table[i][j].setText("" + session.getWorld().getMap()[i][j].getRules().getPriority());
                    }catch(Exception e){
                        table[i][j].setText("0");
                    }
                    panel.add(table[i][j]);
                    
                    
                }
        }
        else
            table = null;
        
        //muut itemit
        filechooser = new JFileChooser();
        optionswindow = new JFrame("Options");
        

        //actionlistenerien määritys
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        
        saveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveasActionPerformed(evt);
            }
        });
        
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        
        options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsActionPerformed(evt);
            }
        });

        //lisätään komponentit toistensa sisään
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(exit);
        
        edit.add(options);

        menu.add(file);
        menu.add(edit);

        container.add(menu, BorderLayout.NORTH);
        container.add(panel);
    }
    
    private void createOptionsComponents(Container container){
        JTabbedPane optionspane = new JTabbedPane();
        JPanel rulestab = new JPanel();
        JPanel ruleslisteditremove = new JPanel();
        JPanel rulesadd = new JPanel();
        JLabel rulesaddtitle = new JLabel();
        JTextField bl = new JTextField();
        JTextField dl = new JTextField();
        JTextField priority = new JTextField();
        JButton add = new JButton();
        ButtonGroup rulesgroup = new ButtonGroup();
        JButton edit = new JButton();
        JButton remove = new JButton();
        
        rulesaddtitle.setText("Add/modify rules here:");
        dl.setText("Dead conditions (separate with comma)");
        bl.setText("Birth conditions (separate with comma)");
        priority.setText("Priority (integer)");
        add.setText("Add");
        edit.setText("Edit");
        remove.setText("Remove");
        
        
        
        rulestab.setLayout(new BorderLayout());
        rulesadd.setLayout(new BoxLayout(rulesadd,BoxLayout.Y_AXIS));
        ruleslisteditremove.setLayout(new BoxLayout(ruleslisteditremove, BoxLayout.Y_AXIS));
        
        
        add.addActionListener(new AddRule(session,dl,bl,priority));

        rulesadd.add(rulesaddtitle);
        rulesadd.add(bl);
        rulesadd.add(dl);
        rulesadd.add(priority);
        rulesadd.add(add);
        
        if(session!=null && session.getWorld()!=null && session.getWorld().getRules()!=null){
            ArrayList<Rules> rules = session.getWorld().getRules();
            for(Rules r : rules){
                JRadioButton tmp = new JRadioButton();
                tmp.setText("Rule " + r.getPriority());
                rulesgroup.add(tmp);
                ruleslisteditremove.add(tmp);
            }
        }
        
        ruleslisteditremove.add(edit);
        ruleslisteditremove.add(remove);
        
        rulestab.add(rulesadd);
        rulestab.add(ruleslisteditremove, BorderLayout.EAST);
        
        optionspane.addTab("Rules", rulestab);
        container.add(optionspane);
    }

    private void openActionPerformed(ActionEvent evt){
        filechooser.setDialogTitle("Open");
        int returnVal = filechooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION){
            File file = filechooser.getSelectedFile();
            session.load(Utilities.correctFilename(file.getName()));
            filename=Utilities.correctFilename(file.getName());
        }
        else
            System.out.println("error: unable to load file\n" + evt.getActionCommand());
    }
    
    private void saveActionPerformed(ActionEvent evt){
        if(filename!=null)
            session.save(Utilities.correctFilename(filename));
        else
            saveasActionPerformed(evt);
    }
    
    private void saveasActionPerformed(ActionEvent evt){
        filechooser.setDialogTitle("Save As ");
        filechooser.setApproveButtonText("Save");
        int returnVal = filechooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION){
            File file = filechooser.getSelectedFile();
            session.save(Utilities.correctFilename(file.getName()));
        }
        else
            System.out.println("error: unabe to save file\n" + evt.getActionCommand());
    }
    
    private void exitActionPerformed(ActionEvent evt){
        System.exit(0);
    }
    
    private void optionsActionPerformed(ActionEvent evt){

        optionswindow.setPreferredSize(new Dimension(500, 350));

        createOptionsComponents(optionswindow.getContentPane());
        optionswindow.pack();
        optionswindow.setVisible(true);
        
    }
}
