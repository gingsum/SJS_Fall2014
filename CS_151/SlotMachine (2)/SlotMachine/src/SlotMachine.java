import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 *Controller class for Slot Machine project. Serves to act as base
 *frame that SlotPanel (view) and Slot (model) will be stacked on
 *top of. 
 * 
 *
 *
 */
public class SlotMachine extends JFrame{

    protected JPanel pnlN, pnlS, pnlE, pnlW;
    private JButton btnPlay;
    private JMenuBar menuBar;
    private JMenu menuGame, menuSkins;
    private JMenuItem newGame, resetGame, exitGame, angryBirdsSkin, darkSoulsSkin; 
    private JSlider slotSlider, symbolSlider;
    ImageIcon bg = new ImageIcon("imgs//skin1/bg.png");
    SlotPanel p;
    int token;
    int winnings;
    
    
    /**
     * 
     * Instantiates SlotMachine
     */
    public SlotMachine(){super();}

    public static void main(String[] args)
    {
        SlotMachine m = new SlotMachine();
        m.initUI();
    }

    
    /**
     * 
     * Initializes the user interface.
     */
    public void initUI()
    {		
        p = new SlotPanel(this);
        slotSlider = new JSlider(JSlider.VERTICAL,4,7,7);
        symbolSlider = new JSlider(JSlider.VERTICAL,6,12,12);
        token = p.token;

        setContentPane(new JLabel(bg));
        setLayout(new BorderLayout());

        setButtons();
        setMenu();	
        setPanels();
        setSliders();

        this.setSize(1166, 500);
        this.setSize(1166, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
    }

    
    /**
     * 
     * Updates the GUI
     */
    public void update()
    {
        p.update();
       
        winnings = p.winnings;
        
        p.repaint();
        setVisible(true);		
    }

    
    /**
     * 
     * Sets the play button.
     */
    private void setButtons()
    {
        btnPlay = new JButton(new ImageIcon("imgs/skin1/play1.png"));
        btnPlay.setOpaque(false);
        //btnPlay.addActionListener(new GreetingListener("Play"));
        btnPlay.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) { token = p.token--;
        	update(); }});
    }

    /**
     * 
     * Sets up the menu.
     */
    private void setMenu()
    {
        menuBar = new JMenuBar();
        menuGame = new JMenu("File");
        menuSkins = new JMenu("Skins");
        newGame = new JMenuItem(new AbstractAction("New Game"){
            public void actionPerformed(ActionEvent e)
            {
                    removeAll();
                    repaint();
                    validate();
            }        
        });
        resetGame = new JMenuItem("Reset Game");
        exitGame = new JMenuItem(new AbstractAction("Exit Game") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        angryBirdsSkin = new JMenuItem("Angry Birds");
        darkSoulsSkin = new JMenuItem("Dark Souls");

        menuGame.add(newGame);
        menuGame.add(resetGame);
        menuGame.add(exitGame);

        menuSkins.add(angryBirdsSkin);
        menuSkins.add(darkSoulsSkin);

        menuBar.add(menuGame);
        menuBar.add(menuSkins);

        this.setJMenuBar(menuBar);		
    }

    /**
     * 
     */
    private void setPanels()
    {
        pnlN = new JPanel();
        pnlN.setOpaque(false);
        pnlN.add(new JLabel("TOKENS: "), BorderLayout.WEST);
        pnlN.add(new JLabel(Integer.toString(token)), BorderLayout.WEST);
        pnlN.add(new JLabel("WINNINGS: "), BorderLayout.EAST);
        pnlN.add(new JLabel(Integer.toString(winnings) + " "), BorderLayout.EAST);

        pnlS = new JPanel();
        pnlS.setOpaque(false);
        pnlS.add(btnPlay);

        pnlE = new JPanel();
        pnlE.setLayout(new BorderLayout());
        pnlE.setOpaque(false);
        pnlE.add(symbolSlider, BorderLayout.NORTH);
        pnlE.add(new JLabel("# of Symbols"), BorderLayout.SOUTH);

        pnlW = new JPanel();
        pnlW.setLayout(new BorderLayout());
        pnlW.setOpaque(false);
        pnlW.add(slotSlider, BorderLayout.NORTH);
        pnlW.add(new JLabel("# of Slots"), BorderLayout.SOUTH);

        this.add(p, BorderLayout.CENTER);
        this.add(pnlN, BorderLayout.NORTH);
        this.add(pnlS, BorderLayout.SOUTH);
        this.add(pnlW, BorderLayout.WEST);
        this.add(pnlE, BorderLayout.EAST);
    }

    /**
     * 
     * Sets sliders.
     */
    private void setSliders()
    {
        slotSlider.setMajorTickSpacing(3);
        slotSlider.setMinorTickSpacing(1);
        slotSlider.setPaintLabels(true);
        slotSlider.setPaintTicks(true);
        slotSlider.setPaintTrack(true);
        slotSlider.setAutoscrolls(true);
        slotSlider.setSnapToTicks(true);
        slotSlider.setOpaque(false);
        slotSlider.addChangeListener(new SlotListener());

        symbolSlider.setMajorTickSpacing(3);
        symbolSlider.setMinorTickSpacing(1);
        symbolSlider.setPaintLabels(true);
        symbolSlider.setPaintTicks(true);
        symbolSlider.setPaintTrack(true);
        symbolSlider.setAutoscrolls(true);
        symbolSlider.setSnapToTicks(true);
        symbolSlider.setOpaque(false);
        symbolSlider.addChangeListener(new SymbolListener());
    }

    
    /**
     * 
     * SlotListener is a custom ChangeListener
     * that let's a JSlider change the number of slots
     * for the next play.
     * 
     *
     */
    class SlotListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider) e.getSource();

            if (!slider.getValueIsAdjusting())
                p.setNumSlots(slider.getValue());
        }
    }

    /**
     * 
     * SymbolListener is a custom ChangeListener
     * that let's a JSlider change the number of symbols
     * that can appear on each slot for the next play.
     * 
     *
     */
    class SymbolListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider) e.getSource();
            if (!slider.getValueIsAdjusting())
                p.setNumSymbols(slider.getValue());            
        }
    }    
}