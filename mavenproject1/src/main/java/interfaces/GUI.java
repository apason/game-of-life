/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import actionlisteners.AddRule;
import actionlisteners.CellActionListener;
import actionlisteners.EditActionListener;
import actionlisteners.GeneralSaveActionListener;
import actionlisteners.NextStepActionListener;
import actionlisteners.RandomizeActionListener;
import actionlisteners.StartActionListener;
import actionlisteners.StopActionListener;
import actionlisteners.WindowCloseActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
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
import logic.World;

/**
 * Ohjelman graafinen käyttöliittymä.
 * @author apa
 */
public class GUI implements Runnable {

    private Session session;
    private String filename;
    private int iterationsperstep;
    private int timeperstep;
    private HashMap<Integer, Color> colormap;

    //main windown komponentit!
    private JFrame frame;
    //framen itemit
    private JMenuBar menu;
    private JPanel panel;
    //menun itemit
    private JMenu file;
    private JMenu edit;
    private JMenu controls;
    //file menun itemit
    private JMenuItem newsession;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem saveas;
    private JMenuItem exit;
    //edit menun itemit
    private JMenuItem options;
    //controls menun itemit
    private JMenuItem nextstep;
    private JMenuItem start;
    private JMenuItem stop;
    private JMenuItem randomize;
    //panelin itemit
    JMenuBar[][] table;
    //muut itemit
    private JFileChooser filechooser;
    private JFrame optionswindow;
    private JColorChooser colorchooser;

    //optionswindown komponentit!
    //options
    private JTabbedPane optionspane;
    //välilehdet
    private JPanel rulestab;
    private JPanel generaltab;
    //rulestabin itemit
    private JPanel ruleslisteditremove;
    private JPanel rulesadd;
    //rulesaddin itemit
    private JLabel rulesaddtitle;
    private JTextField bl;
    private JTextField dl;
    private JTextField priority;
    private JButton add;
    private JButton color;
    //ruleslisteditremoven itemit
    private ButtonGroup rulesgroup;
    private JButton editbutton;
    private JButton remove;
    private JButton rulesok;
    //generaltabin itemit
    private JPanel generaloptions;
    //generaloptionsin itemit
    private JLabel sizetitle;
    private JTextField size;
    private JLabel steptimetitle;
    private JTextField steptime;
    private JLabel iterationstitle;
    private JTextField iterations;
    private JButton generalsave;
    private JButton generalok;

    public GUI() {
        session = new Session();
        //session.setWorld(new World(50,session.getRules())); //aiheuttaa oudon exceptionin
        iterationsperstep = 1;
        timeperstep = 350;
        colormap=new HashMap<Integer,Color>();
    }

    @Override
    public void run() {
        frame = new JFrame("Apa's cellular automata");
        frame.setPreferredSize(new Dimension(650, 425));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    public void createComponents(Container container) {
        container.removeAll();
        //framen itemit
        menu = new JMenuBar();
        panel = new JPanel();
        //menun itemit
        file = new JMenu("File");
        edit = new JMenu("Edit");
        controls = new JMenu("Controls");
        //file menun itemit
        newsession = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveas = new JMenuItem("Save As...");
        exit = new JMenuItem("Exit");
        //edit menun itemit
        options = new JMenuItem("Options");
        //controls menun itemit
        nextstep = new JMenuItem("Next step");
        start = new JMenuItem("Start");
        stop = new JMenuItem("Stop");
        randomize = new JMenuItem("Randomize");
        //panelin itemit
        if (session.getWorld() != null) {
            int size = session.getWorld().getMap().length;
            int prior;
            table = new JMenuBar[size][size];
            panel.setLayout(new GridLayout(size, size));
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    table[i][j]=new JMenuBar();
                    JMenu item = new JMenu();
                    item.setPreferredSize(new Dimension(300,300));
                    try{
                        prior=session.getWorld().getMap()[i][j].getRules().getPriority();
                        item.setText("" + prior);
                        table[i][j].setBackground(colormap.get(prior));
                    }catch(Exception e){
                        item.setText("0");
                    }
                    for(Rules r : session.getRules()){
                        JMenuItem cellitem = new JMenuItem();
                        cellitem.setText(""+r.getPriority());
                        cellitem.setBackground(colormap.get(r.getPriority()));
                        cellitem.addActionListener(new CellActionListener(this,i,j,r));
                        item.add(cellitem);
                    }
                    //kuollut solu
                    JMenuItem cellitem=new JMenuItem();
                    cellitem.setText("0");
                    cellitem.addActionListener(new CellActionListener(this,i,j,null));
                    item.add(cellitem);
                    table[i][j].add(item);
                    panel.add(table[i][j]);

                }
            }
        } else {
            table = null;
        }

        //muut itemit
        filechooser = new JFileChooser();
        optionswindow = new JFrame("Options");
        colorchooser = new JColorChooser();

        optionswindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //actionlistenerien määritys
        newsession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newsessionActionPerformed(evt);
            }
        });
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
        
        nextstep.addActionListener(new NextStepActionListener(this));
        start.addActionListener(new StartActionListener(this));
        stop.addActionListener(new StopActionListener(this));
        randomize.addActionListener(new RandomizeActionListener(this));

        //lisätään komponentit toistensa sisään
        file.add(newsession);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(exit);

        edit.add(options);
        
        controls.add(nextstep);
        controls.add(start);
        controls.add(stop);
        controls.add(randomize);

        menu.add(file);
        menu.add(edit);
        menu.add(controls);

        container.add(menu, BorderLayout.NORTH);
        container.add(panel);
    }

    public void createOptionsComponents(Container container) {
        optionswindow.getContentPane().removeAll(); //pakko
        //options
        optionspane = new JTabbedPane();
        //välilehdet
        rulestab = new JPanel();
        generaltab = new JPanel();
        //rulestabin itemit
        ruleslisteditremove = new JPanel();
        rulesadd = new JPanel();
        //rulesaddin itemit
        rulesaddtitle = new JLabel();
        bl = new JTextField();
        dl = new JTextField();
        priority = new JTextField();
        add = new JButton();
        color = new JButton();
        //ruleslisteditremoven itemit
        rulesgroup = new ButtonGroup();
        editbutton = new JButton();
        remove = new JButton();
        rulesok = new JButton();
        //generaltabin itemit
        generaloptions = new JPanel();
        //generaloptionsin itemit
        sizetitle = new JLabel();
        size = new JTextField();
        steptimetitle = new JLabel();
        steptime = new JTextField();
        iterationstitle = new JLabel();
        iterations = new JTextField();
        generalsave = new JButton();
        generalok = new JButton();

        //rulestab
        rulesaddtitle.setText("Add/modify rules here:");
        dl.setText("Dead conditions (separate with comma)");
        bl.setText("Birth conditions (separate with comma)");
        priority.setText("Priority (integer)");
        add.setText("Add");
        color.setText("Color");
        editbutton.setText("Edit");
        remove.setText("Remove");
        rulesok.setText("Ok");

        //generaltab
        sizetitle.setText("Edge size:");
        if (session.getWorld() != null) {
            size.setText(session.getWorld().getMap().length + "");
        } else {
            size.setText("0");
        }

        steptimetitle.setText("Time per step (ms):");
        steptime.setText(timeperstep + "");
        iterationstitle.setText("Iterations per step:");
        iterations.setText(iterationsperstep + "");
        generalsave.setText("Save");
        generalok.setText("Ok");

        if (session != null && session.getWorld() != null && session.getWorld().getRules() != null) {
            ArrayList<Rules> rules = session.getWorld().getRules();
            for (Rules r : rules) {
                JRadioButton tmp = new JRadioButton();
                tmp.setText("Rule " + r.getPriority());
                rulesgroup.add(tmp);
                ruleslisteditremove.add(tmp);
            }
        }

        rulestab.setLayout(new BorderLayout());
        rulesadd.setLayout(new BoxLayout(rulesadd, BoxLayout.Y_AXIS));
        ruleslisteditremove.setLayout(new BoxLayout(ruleslisteditremove, BoxLayout.Y_AXIS));

        //actionlistenerit
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });
        add.addActionListener(new AddRule(this));
        editbutton.addActionListener(new EditActionListener(this, 1));
        remove.addActionListener(new EditActionListener(this, 0));
        rulesok.addActionListener(new WindowCloseActionListener(this,optionswindow,1));
        generalsave.addActionListener(new GeneralSaveActionListener(this, size, steptime, iterations));
        generalok.addActionListener(new WindowCloseActionListener(this,optionswindow,1));

        //pakataan komponentit toistensa sisään
        rulesadd.add(rulesaddtitle);
        rulesadd.add(bl);
        rulesadd.add(dl);
        rulesadd.add(priority);
        rulesadd.add(color);
        rulesadd.add(add);

        ruleslisteditremove.add(editbutton);
        ruleslisteditremove.add(remove);

        generaltab.setLayout(new BoxLayout(generaltab, BoxLayout.Y_AXIS));
        generaltab.add(sizetitle);
        generaltab.add(size);
        generaltab.add(steptimetitle);
        generaltab.add(steptime);
        generaltab.add(iterationstitle);
        generaltab.add(iterations);
        generaltab.add(generalsave);
        generaltab.add(generalok);

        rulestab.add(rulesadd);
        rulestab.add(ruleslisteditremove, BorderLayout.EAST);
        rulestab.add(rulesok, BorderLayout.SOUTH);

        generaltab.add(generaloptions);

        optionspane.addTab("Rules", rulestab);
        optionspane.addTab("General", generaltab);
        container.add(optionspane);
    }

    //tapahtumien käsittely
    private void newsessionActionPerformed(ActionEvent evt){
        session=new Session();
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    
    private void openActionPerformed(ActionEvent evt) {
        filechooser.setDialogTitle("Open");
        int returnVal = filechooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            session.load(Utilities.correctFilename(file.getName()));
            filename = Utilities.correctFilename(file.getName());
        } else {
            System.out.println("error: unable to load file\n" + evt.getActionCommand());
        }
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void saveActionPerformed(ActionEvent evt) {
        if (filename != null) {
            session.save(Utilities.correctFilename(filename));
        } else {
            saveasActionPerformed(evt);
        }
    }

    private void saveasActionPerformed(ActionEvent evt) {
        filechooser.setDialogTitle("Save As ");
        filechooser.setApproveButtonText("Save");
        int returnVal = filechooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            session.save(Utilities.correctFilename(file.getName()));
        } else {
            System.out.println("error: unabe to save file\n" + evt.getActionCommand());
        }
    }
    private void colorActionPerformed(ActionEvent evt){
        
        Color rulescolor = JColorChooser.showDialog(color, "Choose cells color", color.getBackground());
        color.setBackground(rulescolor);
    }

    private void exitActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void optionsActionPerformed(ActionEvent evt) {

        optionswindow.setPreferredSize(new Dimension(500, 350));

        createOptionsComponents(optionswindow.getContentPane());
        optionswindow.pack();
        optionswindow.setVisible(true);

    }

    public void setTimePerStep(int timeperstep) {
        this.timeperstep = timeperstep;
    }

    public void setIterationsPerStep(int iterationsperstep) {
        this.iterationsperstep = iterationsperstep;
    }

    public Session getSession() {
        return session;
    }

    public JFrame getOptionsWindow() {
        return optionswindow;
    }

    public JTextField getBl() {
        return bl;
    }

    public JTextField getDl() {
        return dl;
    }

    public JTextField getPriority() {
        return priority;
    }
    public ButtonGroup getRulesGroup(){
        return rulesgroup;
    }
    public JFrame getFrame(){
        return frame;
    }
    public int getTimePerStep(){
        return timeperstep;
    }
    public int getIterationsPerStep(){
        return iterationsperstep;
    }
    public HashMap<Integer,Color> getColorMap(){
        return colormap;
    }
    public JButton getColor(){
        return color;
    }
}

