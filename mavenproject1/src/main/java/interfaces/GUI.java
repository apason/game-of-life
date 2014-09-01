package interfaces;

import actionlisteners.CellActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
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
 *
 * @author apa
 */
public class GUI implements Runnable {

    /** Session olio, jonka kanssa yhteistyössä käyttöliittymä toimii */
    private Session session;
    /** Jos peli ladataan tiedostosta sisältää filename tiedoston josta lataus tapahtui.
     *  Kun session tallennetaan "save" valikosta, tapahtuu tallennus tämän nimen määrittelemään tiedostoon.
     */
    private String filename;
    /** Ajonaikainen muuttuja, joka sisältää tiedon montako pelin iteraatiota suoritetaan Sessionin
     *  run() metoissa piirtojen välillä. Tätä tietoa ei tallenneta tiedostoon sessionin tallennuksen yhteydessä.
     */
    private int iterationsperstep;
    /** Ajonaikainen muuttuja, joka sisältää tiedon montako millisekuntia Sessionin run() metodissa
     * odotetaan piirtokertojen välillä. Tätä tietoa ei tallenneta tiedostoon.
     */
    private int timeperstep;
    /** Ajonaikainen olio, jossa on tieto eri solutyypeille määritellyt värit.
     *  Tätä tietoa ei tallenneta tiedostoon.
     */
    private HashMap<Integer, Color> colormap;
    
    /** purkka ratkaisu viewprioritiesin tilan säilyttämiseksi kun pääkomponentit tehdään uudestaan */
    private boolean sp;

    //main windown komponentit!
    /** Ohjelman pääikkuna */
    private JFrame frame;
    //framen itemit
    /** Pääikkunan menu. Sisältää valikot file, edit ja controls */
    private JMenuBar menu;
    /** Alue pääikkunassa johon solut piirretään */
    private JPanel panel;
    //menun itemit
    /** Päämenun file valikko. Sisältää toiminnot new, open, save, save as ja exit */
    private JMenu file;
    /** Päämenun edit valikko. Sisältää toiminnon options, joka avaa asetusikkunan */
    private JMenu edit;
    /** päämenun controls valikko. Sisältää toiminnot next step, start, stop, randomize ja clear */
    private JMenu controls;
    /** päämenun view valikko */
    private JMenu view;
    //file menun itemit
    /** file menun new painike. Luo uuden Sessionin oletusasetuksilla */
    private JMenuItem newsession;
    /** file menun open painike. Lataa käytäjän valitseman session tiedostojärjestelmästä. */
    private JMenuItem open;
    /** file menun save painike. Tallentaa Sessionin muuttujan filename @See filename 
     *  määrittelemään tieodstoon. Jos filename on null, niin ajaa toiminnon save as @see saveas
     */
    private JMenuItem save;
    /** file menun save as painike. Tallentaa Sessionin käyttäjän valitsemaan tiedostoon */
    private JMenuItem saveas;
    /** file menun exit painike. Lopettaa ohjelman suorituksen. */
    private JMenuItem exit;
    //edit menun itemit
    /** edit menun painike options. Avaa options ikkunan */
    private JMenuItem options;
    //controls menun itemit
    /** controls menun next step painike. Laskee ja piirtää maailman seuraavan askeleen. */
    private JMenuItem nextstep;
    /** controls menun start painike. Aloittaa maailman simuloinnin. */
    private JMenuItem start;
    /** controls menun stop painike. Lopettaa maailman simuloinnin */
    private JMenuItem stop;
    /** controls menun randomize painike. Luo satunnaisen maailman (sääntöjen mukaan). */
    private JMenuItem randomize;
    /** controls menun clear painike. Muuttaa kaikki maailman solut kuolleiksi. */
    private JMenuItem clear;
    //view menun itemit
    private JCheckBoxMenuItem viewpriorities;
    //panelin itemit
    /** Jokaiselle solulle oma JMenuBar */
    JMenuBar[][] table;
    //muut itemit
    /** JFileChooser lataus- ja tallennustiedostojen valintaa varten */
    private JFileChooser filechooser;
    /** Options ikkunan pääkomponentti */
    private JFrame optionswindow;

    //optionswindown komponentit!
    //options
    /** optionswindow:ssa on vain tämä komponentti. Sisältää välilehdet, jotka sisältävät muut komponentit */
    private JTabbedPane optionspane;
    //välilehdet
    /** (solujen) sääntöjen määrittely tapahtuu tässä välilehdessä */
    private JPanel rulestab;
    /** Muiden sääntöjen määrittely tapahtuu tässä välilehdessä */
    private JPanel generaltab;
    //rulestabin itemit
    /** Täältä löytyy kaikki käytössäolevat säännöt ja painikkeet edit sekä remove niiden muokkaamiseen / poistmiseen. */
    private JPanel ruleslisteditremove;
    /** Täältä löytyy kentät sääntöjen lisäämiseen. */
    private JPanel rulesadd;
    //rulesaddin itemit
    /** Otsikko kentille */
    private JLabel rulesaddtitle;
    /** Tekstikenttä johon käyttäjä syöttää tiedot syntymäsäännöistä */
    private JTextField bl;
    /** Tekstikenttä johon käyttäjä syöttää tiedot kuolemasäännöistä */
    private JTextField dl;
    /** Tekstikenttä johon käyttäjä syöttää säännön prioriteetin */
    private JTextField priority;
    /** Painike jolla lisätään uusi sääntö Sessionille */
    private JButton add;
    /** Painike joka avaa JColorChooserin värin määritystä varten */
    private JButton color;
    //ruleslisteditremoven itemit
    /** Kaikkille olemassaoleville säännöille on oma JButton. Kyseiset JButtonit kuuluvat
     * tähän buttongroupiin, jotta saadaan rajoitettua valinta yhteen sääntöön kerrallaan.
     */
    private ButtonGroup rulesgroup;
    /** Painike joka siirtää valitun säännön editoitavaksi */
    private JButton editbutton;
    /** Painike joka poistaa valitun säännön */
    private JButton remove;
    /** painike Joka sulkee Options ikkunan */
    private JButton rulesok;
    //generaltabin itemit
    /** Täältä löytyy kentät muiden kuin sääntöjen muuttamiseen */
    private JPanel generaloptions;
    //generaloptionsin itemit
    /** Otsikko kentän koon määritykselle @See size */
    private JLabel sizetitle;
    /** Tekstikenttä johon käyttäjä syöttää tiedon maailman koosta @See size */
    private JTextField size;
    /** Otsikko timeperstepin määrittämiseksi @See timeperstep */
    private JLabel steptimetitle;
    /** Tekstikenttä johon käyttäjä syöttää tiedon steptimestä. @See timeperstep */
    private JTextField steptime;
    /** Otsikko iterationsperstepin määrittämiseksi. @See iterationsperstep */
    private JLabel iterationstitle;
    /** Tekstikenttä johon käyttäjä syöttää tiedon iterationsperstepistä. @See iterationsperstep */
    private JTextField iterations;
    /** Painike joka tallentaa tehdyt muutokset Sessioniin. */
    private JButton generalsave;
    /** Painike joka sulkee options ikkunan. */
    private JButton generalok;

    /**
     * Luokan konstruktori. Luo käyttöliittymälle uuden session olion, Sessionille maailman,
     * ja luo colormapin @See colormap Alustaa myös oletusasetukset.
     */
    public GUI() {
        session = new Session(this);
        session.setWorld(new World(3, session.getRules())); //aiheuttaa oudon exceptionin
        iterationsperstep = 1;
        timeperstep = 350;
        colormap = new HashMap<Integer, Color>();
        sp=true;
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

    public ButtonGroup getRulesGroup() {
        return rulesgroup;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getTimePerStep() {
        return timeperstep;
    }

    public int getIterationsPerStep() {
        return iterationsperstep;
    }

    public HashMap<Integer, Color> getColorMap() {
        return colormap;
    }

    public JButton getColor() {
        return color;
    }

    /**
     * Runnable rajapinnan toteutus. Luo ja piirtää käyttöliittymäkomponentit
     */
    @Override
    public void run() {

        createWindows();

        createComponents();
        
    }

    /**
     * Luo pääikkunan ja options ikkunan, sekä asettaa näille nimet ja koot.
     */
    private void createWindows() {
        frame = new JFrame("Apa's cellular automata");
        frame.setPreferredSize(new Dimension(650, 650));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        optionswindow = new JFrame("Options");
        optionswindow.setPreferredSize(new Dimension(500, 350));
        optionswindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Luo pääikkunan komponentit.
     */
    public void createComponents() {
        frame.getContentPane().removeAll();
        //menu
        createMainMenu();
        //panelin itemit
        createCells();
        //muut itemit
        createOthers();
        //actionlistenerien määritys
        addMainActionListeners();
        //lisätään komponentit toistensa sisään
        pack();
    }

    /**
     * Luo pääikkunan menun komponentit.
     */
    private void createMainMenu() {
        //framen itemit
        menu = new JMenuBar();
        //menun itemit
        file = new JMenu("File");
        edit = new JMenu("Edit");
        controls = new JMenu("Controls");
        view = new JMenu("View");
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
        clear = new JMenuItem("Clear");
        //view menun itemit
        viewpriorities = new JCheckBoxMenuItem("View priorities");   
        viewpriorities.setSelected(sp);
    }

    /**
     * Luo pääikkunan solutaulukone.
     */
    public void createCells() {
        if (panel != null) {
            frame.getContentPane().remove(panel);
        }
        panel = new JPanel();
        //panelin itemit
        if (session.getWorld() != null) {
            int size = session.getWorld().getMap().length;
            int prior;
            table = new JMenuBar[size][size];
            panel.setLayout(new GridLayout(size, size));
            boolean viewprioritiess = viewpriorities.isSelected();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    table[i][j] = new JMenuBar();
                    JMenu item = new JMenu();
                    item.setPreferredSize(new Dimension(300, 300));
                    try {
                        prior = session.getWorld().getMap()[i][j].getRules().getPriority();
                        if(viewprioritiess)
                            item.setText("" + prior);
                        table[i][j].setBackground(colormap.get(prior));
                    } catch (Exception e) {
                        if(viewprioritiess)
                            item.setText(".");
                        table[i][j].setBackground(Color.WHITE);
                    }
                    for (Rules r : session.getRules()) {
                        JMenuItem cellitem = new JMenuItem();
                        cellitem.setText("" + r.getPriority());
                        cellitem.setBackground(colormap.get(r.getPriority()));
                        cellitem.addActionListener(new CellActionListener(this, i, j, r));
                        item.add(cellitem);
                    }
                    //kuollut solu
                    JMenuItem cellitem = new JMenuItem();
                    cellitem.setText("Dead");
                    cellitem.addActionListener(new CellActionListener(this, i, j, null));
                    item.add(cellitem);
                    table[i][j].add(item);
                    panel.add(table[i][j]);

                }
            }
        } else {
            table = null;
        }
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    /**
     * Luo muut ohjelman käyttämät komponentit.
     */
    private void createOthers() {
        filechooser = new JFileChooser();

    }

    /**
     * Lisää actionlistenerit pääikkunan komponenteille.
     */
    private void addMainActionListeners() {
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

        nextstep.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                nextstepActionListener(ae);
            }
        });
        
        start.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startACtionPerformed(ae);
            }
        });
        
        stop.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                stopACtionPerformed(ae);
            }
        });
        
        randomize.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                randomizeActionPerformed(ae);
            }
        });
        
        clear.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        
        viewpriorities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewprioritiesActionPerformed(evt);
            }
        });
    }

    /**
     * Pakkaa pääikkunan komponentit toistensa sisään.
     */
    private void pack() {
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
        controls.add(clear);
        
        view.add(viewpriorities);

        menu.add(file);
        menu.add(edit);
        menu.add(controls);
        menu.add(view);

        frame.getContentPane().add(menu, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luo options ikkunan komponentit.
     */
    public void createOptionsComponents() {
        optionswindow.getContentPane().removeAll();
        //luodaan komponentit
        createTabbedPane();
        //annetaan alkuarvot ja nimet komponenteille.
        setOptionsValues();
        //action- ja focuslistenerit
        addOptionsListeners();
        //lisätään komponentit toistensa sisään
        packOptions();

    }

    /**
     * Luo JTabbedPanen ja sen alikomponentit optionswindowlle.
     */
    private void createTabbedPane() {
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
    }

    /**
     * Antaa arvot optionswindown komponenteille.
     */
    private void setOptionsValues() {
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
    }

    /**
     * Lisää actionlistenerit optionswindown komponenteille.
     */
    private void addOptionsListeners() {
        //actionlistenerit
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });
        
        add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                editActionListener(ae);
            }
        });
        remove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeActionListener(ae);
            }
        });
        
        rulesok.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                wcActionPerformed(ae, optionswindow);
            }
        });
        
        generalsave.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                generalsaveActionPerformed(ae);
            }
        });
        
        generalok.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                wcActionPerformed(ae, optionswindow);
            }
        });
        
        //focuslistenerit
        bl.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (bl.getText().contains("Birth")) {
                    bl.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        dl.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dl.getText().contains("Dead")) {
                    dl.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        priority.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (priority.getText().contains("Priority")) {
                    priority.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
    }

    /**
     * Pakkaa optionsikkunan komponentit toistensa sisään.
     */
    private void packOptions() {
        rulestab.setLayout(new BorderLayout());
        rulesadd.setLayout(new BoxLayout(rulesadd, BoxLayout.Y_AXIS));
        ruleslisteditremove.setLayout(new BoxLayout(ruleslisteditremove, BoxLayout.Y_AXIS));

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
        optionswindow.getContentPane().add(optionspane);

        optionswindow.pack();
        optionswindow.setLocationRelativeTo(frame);
        optionswindow.setVisible(true);
    }

    //tapahtumien käsittely
    /**
     * new session painikkeen suorittama koodi.
     * @param evt kseinen ActionEvent
     */
    private void newsessionActionPerformed(ActionEvent evt) {
        session = new Session(this);
        createComponents();
    }

    /**
     * open painikkeen suorittama koodi.
     * @param evt Kyseinen ActionEvent
     */
    private void openActionPerformed(ActionEvent evt) {
        session.stop();
        filechooser.setDialogTitle("Open");
        filechooser.setApproveButtonText("Open");
        int returnVal = filechooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            session.load(file.getAbsolutePath());
            filename = file.getAbsolutePath();
        } else {
            System.out.println("File was not opened!\n" + evt.getActionCommand());
        }
        createComponents();
    }

    /**
     * save painikkeen suorittama koodi.
     * @param evt Kyseinen ActionEvent
     */
    private void saveActionPerformed(ActionEvent evt) {
        if (filename != null) {
            session.save(Utilities.correctFilename(filename));
        } else {
            saveasActionPerformed(evt);
        }
    }

    /**
     * save as painikkeen suorittama koodi.
     * @param evt Kyseinen ActionEvent
     */
    private void saveasActionPerformed(ActionEvent evt) {
        filechooser.setDialogTitle("Save As ");
        filechooser.setApproveButtonText("Save");
        int returnVal = filechooser.showOpenDialog(frame);
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            session.save(Utilities.correctFilename(file.getAbsolutePath()));
        } else {
            System.out.println("File was not saved!\n" + evt.getActionCommand());
        }
    }
    
    /**
     * exit painikkeen suorittama koodi.
     * @param evt Kyseinen ActionEvent
     */
    private void exitActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    /**
     * options painikkeen suorittama koodi. 
     * @param evt Kyseinen ActionEvent
     */
    private void optionsActionPerformed(ActionEvent evt) {
        session.stop();
        createOptionsComponents();
    }
    
    private void nextstepActionListener(ActionEvent evt){
        session.stop();
        session.getWorld().evolve();
        updateCells();
    }
    
    private void startACtionPerformed(ActionEvent evt){
        if(!session.getRunning()){
            Thread thread = new Thread(session);
            thread.start();
        }
    }
    
    private void stopACtionPerformed(ActionEvent evt){
        session.stop();
    }
     
    private void randomizeActionPerformed(ActionEvent evt){
        session.stop();
        session.getWorld().randomizeMap();
        updateCells();
    }
    
    private void clearActionPerformed(ActionEvent evt){
        session.getWorld().clear();
        updateCells();
    }
    
    private void viewprioritiesActionPerformed(ActionEvent evt){
        sp = sp != true;
        updateCells();
    }
    
    private void generalsaveActionPerformed(ActionEvent evt){
        if(!Utilities.correctSize(size.getText())){
            size.setText("Error: Size must be integer [2,99]");
            return;
        }
        
        if(!Utilities.correctIterations(iterations.getText())){
            iterations.setText("Error: Value must be integer: [1,999");
            return;
        }
        
        if(!Utilities.correctSteptime(steptime.getText())){
            steptime.setText("Error: Value must be integer [1,1999]");
            return;
        }
        
        if(session.getWorld()!=null && session.getWorld().getMap().length!=Integer.parseInt(size.getText())){
            //varoitus ylikirjoituksesta
            final JFrame frame = new JFrame("Owerwrite?");
            JLabel label = new JLabel();
            JButton ok = new JButton();
            JButton cancel = new JButton();
            label.setText("To change the size you will lost current world!");
            ok.setText("Ok");
            cancel.setText("Cancel");
            
            ok.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    generalSaveConfirmActionPerformed(ae, frame);
                }
            });

            cancel.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    cancelActionPerformed(ae, frame);
                }
            });
            
            frame.getContentPane().add(label, BorderLayout.NORTH);
            frame.getContentPane().add(ok, BorderLayout.WEST);
            frame.getContentPane().add(cancel, BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(optionswindow);
            frame.setVisible(true);
        }
        else if(session.getWorld()==null)
            session.createWorld(Integer.parseInt(size.getText()));
        

        setIterationsPerStep(Integer.parseInt(iterations.getText()));
        setTimePerStep(Integer.parseInt(steptime.getText()));
    }
    
    /**
     * color painikkeen suorittama koodi.
     * @param evt Kyseinen ActionEvent
     */
    private void colorActionPerformed(ActionEvent evt) {
        Color rulescolor = JColorChooser.showDialog(color, "Choose cells color", color.getBackground());
        color.setBackground(rulescolor);
    }
    
    
    private void addActionPerformed(ActionEvent evt){
        if(!Utilities.correctConditionList(bl.getText())){
            bl.setText("Syntax error: list conditions (integers) separated with comma.");
            return;
        }
        if(!Utilities.correctConditionList(dl.getText())){
            dl.setText("Syntax error: list conditions (integers) separated with comma.");
            return;
        }
        if(!Utilities.correctPriority(priority.getText())){
            priority.setText("Syntax error: priority should be integer [0,12]");
            return;
        }
        
        ArrayList<Integer> bl = new ArrayList<Integer>();
        ArrayList<Integer> dl = new ArrayList<Integer>();
        Rules rule;
        int prior = Integer.parseInt(priority.getText());
        String[] tmp = this.bl.getText().split(",");
        for(String s : tmp)
            bl.add(Integer.parseInt(s));
        tmp = this.dl.getText().split(",");
        for(String s : tmp)
            dl.add(Integer.parseInt(s));
        this.bl.setText("");
        this.dl.setText("");
        priority.setText("");
        rule=new Rules(bl,dl,prior);
        try{
           session.addRule(rule);
            colormap.put(prior, color.getBackground());
            createOptionsComponents();
            getOptionsWindow().pack();
            getOptionsWindow().setVisible(true);
        }catch (Exception e){
            final JFrame frame;
            frame = new JFrame("Error");
            JLabel label = new JLabel();
            JButton button = new JButton();
            label.setText("Unable to add rule:\n" + e.toString());
            button.setText("Ok");
            
            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    wcActionPerformed(ae, frame);
                }
            });
                        
            frame.getContentPane().add(label, BorderLayout.NORTH);
            frame.getContentPane().add(button);
            frame.pack();
            frame.setVisible(true);
            
        }
    }
    
    private void editActionListener(ActionEvent evt){
        String s = getSelectedButtonText(rulesgroup);
        Rules del = null;
        
        if(s==null)
            return;
        
        String[] st = s.split(" ");
        int d = Integer.parseInt(st[1]);
        for (Rules r : session.getRules()) {
            if (r.getPriority() == d) {
                del = r;
                break;
            }
        }
        
        session.getRules().remove(del);
        createOptionsComponents();
        if (del!=null) {
            bl.setText(Utilities.listToString(del.getBirth()));
            dl.setText(Utilities.listToString(del.getDie()));
            priority.setText(del.getPriority() + "");
            color.setBackground(colormap.get(del.getPriority()));
        }
        colormap.remove(del.getPriority());
        optionswindow.pack();
        optionswindow.setVisible(true);
    }
    
    private void removeActionListener(ActionEvent evt){
        String s = getSelectedButtonText(rulesgroup);
        Rules del = null;
        
        if(s==null)
            return;
        
        String[] st = s.split(" ");
        int d = Integer.parseInt(st[1]);
        for (Rules r : session.getRules()) {
            if (r.getPriority() == d) {
                del = r;
                break;
            }
        }
        
        session.getRules().remove(del);
        createOptionsComponents();
        colormap.remove(del.getPriority());
        optionswindow.pack();
        optionswindow.setVisible(true);
    }
    
    private void generalSaveConfirmActionPerformed(ActionEvent ae, JFrame frame){
       session.createWorld(Integer.parseInt(size.getText()));
        frame.setVisible(false);
    }
    
    private void cancelActionPerformed(ActionEvent ae, JFrame frame){
        frame.setVisible(false);
    }
    
    private void wcActionPerformed(ActionEvent ae, JFrame frame){
        session.getWorld().removeExtras();

            createComponents();
            getFrame().pack();
            getFrame().setVisible(true);
       

        frame.setVisible(false);
    }
    
    
    /**
     * Päivittää solutaulukon.
     */
    public void updateCells() {
        //panelin itemit
        if (session.getWorld() != null) {
            int size = session.getWorld().getMap().length;
            int prior;
            boolean viewprioritiess = viewpriorities.isSelected();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    JMenu item = table[i][j].getMenu(0);
                    try {
                        prior = session.getWorld().getMap()[i][j].getRules().getPriority();
                        if(viewprioritiess)
                            item.setText("" + prior);
                        else
                            item.setText("");
                        table[i][j].setBackground(colormap.get(prior));
                    } catch (Exception e) {
                        if(viewprioritiess)
                            item.setText(".");
                        else
                            item.setText("");
                        table[i][j].setBackground(Color.WHITE);
                    }
                    for (Rules r : session.getRules()) {
                        JMenuItem cellitem = item.getItem(session.getRules().indexOf(r));
                        cellitem.setText("" + r.getPriority());
                        cellitem.setBackground(colormap.get(r.getPriority()));
                        cellitem.removeActionListener(cellitem.getActionListeners()[0]);
                        cellitem.addActionListener(new CellActionListener(this, i, j, r));
                    }
                    //kuollut solu
                    JMenuItem cellitem = item.getItem(session.getRules().size());
                    cellitem.setText("Dead");
                    cellitem.removeActionListener(cellitem.getActionListeners()[0]);
                    cellitem.addActionListener(new CellActionListener(this, i, j, null));
                }
            }
        }
    }
    
    /**
     * Luo uuden ikkunan ja errormessagelle
     * @param message Ikkunassa näytettävä viesti
     */
    public void createErrorWindow(String message){
        final JFrame errorwindow = new JFrame("Error");
        errorwindow.setLayout(new BorderLayout());
        JLabel error = new JLabel("Error: " + message);
        errorwindow.getContentPane().add(error,BorderLayout.NORTH);
        JButton b = new JButton("Ok");
        b.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                errorwindow.setVisible(false);
            }
        });
        errorwindow.add(b,BorderLayout.SOUTH);
        errorwindow.pack();
        errorwindow.setLocationRelativeTo(frame);
        errorwindow.setAlwaysOnTop(true);
        errorwindow.setVisible(true);
    }
    
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}

