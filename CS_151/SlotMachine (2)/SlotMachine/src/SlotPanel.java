import java.util.ArrayList;
import javax.swing.JPanel;

public class SlotPanel extends JPanel {
	
    private int numSlots;
    private int numSymbols;
    protected int winnings;
    public static int token;
    private ArrayList<Slot> slots;
    ArrayList<Integer> faceIndices;
    protected Data model;
    private SlotMachine control;
    
    /**
     * 
     * SlotPanel acts as the view; displays Slot objects.
     * @param machine controller for SlotPanel to be displayed on.
     */
    public SlotPanel(SlotMachine machine)
    {
        super();
        this.setOpaque(false);
        token = 10;
        winnings = 0;
        numSlots = 7;
        numSymbols = 12;
        slots = new ArrayList<Slot>();
        faceIndices = new ArrayList<Integer>();
        model = new Data(numSlots, this, control);
        control = machine;
        slotLoader();
    }

    /**
     * 
     * Loads slot objects.
     */
    private void slotLoader()
    {
        for (int i = 0; i < numSlots; i++)
        {
            slots.add(new Slot(i, numSymbols, this));
            this.add(slots.get(i));
        }	
    }

    /**
     * Updates SlotPanel with changed Slot objects.
     */
    public void update()
    {
        model = new Data(numSlots, this, control);
        removeAll();
        slots.removeAll(slots);
        faceIndices.removeAll(faceIndices);
        slotLoader();
        for (int i = 0; i < slots.size(); i++)
            slots.get(i).update();
        this.repaint();
    }

    /*
     * Sets number of slots.
     */
    public void setNumSlots(int num){numSlots = num;}
    
    /*
     * Sets number of symbols.
     */
    public void setNumSymbols(int n){numSymbols = n;}
    
    /*
     * Gets number of slots.
     */
    public int getNumSlots(){return numSlots;}
    
    /*
     * Gets number of symbols.
     */
    public int getNumSymbols(){return numSymbols;}
}
