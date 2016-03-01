import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Slot extends JPanel implements  Runnable {

    private int numSymbols;
    protected int faceIndex;
    private int slotIndex;
    private JLabel slotFace;
    private JButton slotButton;
    private ArrayList<Icon> slotSymbols;
    private SlotPanel slotPanel;
    
    /**
     * Slot is a custom JPanel that contains its own face image,
     * and a button to change it.
     * 
     * @param index the index of the face icon
     * @param symbols the number of symbols
     * @param slotPanel the panel to be placed on
     */
    public Slot(int index, int symbols, SlotPanel slotPanel)
    {
        super();
        this.slotPanel = slotPanel;
        numSymbols = symbols;
        slotIndex = index;
        slotSymbols = new ArrayList<Icon>();
        slotFace = new JLabel();
        slotButton = new JButton("Stop");
        initSlot();
    }

    /**
     * 
     * Initializes slot
     */
    private void initSlot()
    {
        setLayout(new BorderLayout());
        setOpaque(false);
        symbolLoader();
        startSlot();
    }

    /*
     * Updates slot
     */
    public void update()
    {
        remove(slotFace);
        slotFace.setIcon(new ImageIcon("imgs/skin1/birds.gif"));
        this.add(slotFace, BorderLayout.NORTH);
        this.add(slotButton, BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }

    private void symbolLoader()
    {
        try
        {
            Scanner in = new Scanner(new File("imgs//skin1/slots.txt"));
            while (in.hasNextLine())
            {
                String input = in.nextLine();
                slotSymbols.add(new ImageIcon(input));			
            }
            in.close();
        }catch (FileNotFoundException e){e.printStackTrace();}		
    }

    /*
     * Initial slot spin when game is started.
     */
    private void startSlot()
    {
        int rand = (int)( numSymbols * Math.random());
        slotFace.setIcon(slotSymbols.get(rand));
        this.add(slotFace, BorderLayout.NORTH);
        slotButton.addMouseListener(new SlotListener());
        setVisible(true);
    }	

    /*
     * Spins slot when game is played.
     */
    private void spinSlot()
    {
        remove(slotFace);
        faceIndex = (int)( numSymbols * Math.random());
        slotFace.setIcon(slotSymbols.get(faceIndex));
        this.add(slotFace, BorderLayout.NORTH);
        repaint();
        slotPanel.model.setIndice(slotIndex, faceIndex);
        slotPanel.model.setTracker(slotIndex, false);
        slotPanel.model.checkTracker();
    }

    public void setNumSymbols(int num){numSymbols = num;}
    public int getFaceIndex(){return faceIndex;}
    public int getNumSymbols(){return numSymbols;}
    private void reSize(){this.setSize(slotFace.getWidth(), slotFace.getHeight());}

    /*
     * SlotListener is a custom MouseListener that activates the
     * multithreaded run() method to stop a slot when its stop
     * button is pressed.
     */
    class SlotListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {run();}
        @Override
        public void mouseReleased(MouseEvent e) {}
    }

    @Override
    /**
     * Stops slot, removes its stop button.
     */
    public void run() {
        
    	spinSlot();
        remove(slotButton);
        reSize();
        setVisible(true);
    }
}