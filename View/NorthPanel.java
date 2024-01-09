package View;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import View.Components.*;

/**
 * The {@code NorthPanel} class represents a Swing JPanel that serves as the top panel
 * in a graphical user interface, typically used for displaying player information.
 * It contains a PlayerGUI instance representing player 2.
 * Class implemented by Asim Adel Ahmed Maroof
 */

public class NorthPanel extends JPanel{
   private PlayerGUI player;

    /**
     * Constructs a new NorthPanel instance.
     * Initializes the panel with a PlayerGUI instance representing player 2.
     *
     * @param player2 The PlayerGUI instance representing player 2.
     */
    public NorthPanel(PlayerGUI player) {
        setLayout(new BorderLayout());
        this.player = player;
        add(this.player);
    }

    
    public void setPlayerGUI(PlayerGUI player){
        remove(this.player);
        this.player = player;
        add(this.player);
        repaint();
    }

}
