import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Data {
    private int[] indices;
    private boolean[] indiceTracker;
    private SlotPanel slotPanel;
    private SlotMachine control;
    ImageIcon winImg = new ImageIcon("imgs//skin1/gamewin.png");
    ImageIcon loseImg = new ImageIcon("imgs//skin1/gamelose.png");
    
    /**
     * Handles all calculations required by slot machine
     * @param length number of slots
     * @param slotPanel slot panel that slots are located on
     * @param machine slot machine panel is located on
     */
    public Data (int length, SlotPanel slotPanel, SlotMachine machine){
        indices = new int[length];
        indiceTracker = new boolean[length];
        this.slotPanel = slotPanel;
        control = machine;
        
        for(int i = 0; i < indiceTracker.length; i++)
            indiceTracker[i] = true;
    }
    
    /*
     * Checks to see if slot machine produces a win.
     */
    public void checkTracker(){
        for(int i = 0; i < indiceTracker.length; i++)
        {
            if(indiceTracker[i] != false)
                break;
            else if(i == indiceTracker.length-1)
                calcWinnings();
        }
    }
    
    /*
     * Calculates slot machine winnings.
     */
    public void calcWinnings(){
        int currentValue;
        int count;
        int winnings = 0;
        int temp = winnings;
        
        
        for(int i = 0; i < indices.length-2; i++)
        {
            currentValue = indices[i];
            count = 0;
            for(int j = i; j < indices.length; j++)
                if(currentValue == indices[j])
                    count++;
         
            if(count >= 3)
                winnings = winnings + count*currentValue;
        }
        
        slotPanel.winnings = winnings;
        slotPanel.token = slotPanel.token + winnings;
        
        control.pnlN.removeAll();
        control.pnlN.setOpaque(false);
        control.pnlN.add(new JLabel("TOKENS: "), BorderLayout.WEST);
        control.pnlN.add(new JLabel(Integer.toString(control.p.token)), BorderLayout.WEST);
        control.pnlN.add(new JLabel("WINNINGS: "), BorderLayout.EAST);
        control.pnlN.add(new JLabel(Integer.toString(control.p.winnings)), BorderLayout.EAST);
        control.add(control.pnlN);
        
        if(winnings > 0)
        {
            JOptionPane.showMessageDialog(
            null, // parent window
            "", // message
            "You Win " + winnings + " Tokens!!", // window title
            JOptionPane.INFORMATION_MESSAGE, // message type
            winImg);
        }
       if(temp == winnings)
        {
            JOptionPane.showMessageDialog(
            null, // parent window
            "", // message
            "You Lose!", // window title
            JOptionPane.INFORMATION_MESSAGE, // message type
            loseImg);
        }
        
        control.setVisible(true);
    }
    
    public int getIndice(int index){return indices[index];}
    public boolean getTracker(int index){return indiceTracker[index];}
    public void setIndice(int index, int indice){ indices[index] = indice;}
    public void setTracker(int index, boolean track){ indiceTracker[index] = track;}
}