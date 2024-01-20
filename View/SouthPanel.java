package View;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import View.Components.*;

/**
 * The {@code SouthPanel} class represents a Swing JPanel that serves as the bottom panel
 * in a graphical user interface, typically used for displaying player 1 information and control buttons.
 * It contains a PlayerGUI instance for player 1 and buttons for actions like New, Save, Quit, and Resign.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public class SouthPanel extends JPanel {
    private PlayerGUI player;

     /**
     * Constructs a new SouthPanel instance.
     * Initializes the panel with a BorderLayout and adds a PlayerGUI for player 1 to the NORTH region.
     * Adds buttons for New, Save, Quit, and Resign to the SOUTH region.
     *
     * @param player1 The PlayerGUI instance representing player 1.
     */

    public SouthPanel(PlayerGUI player) {
        setLayout(new BorderLayout());
        this.player = player;
        add(this.player, BorderLayout.NORTH);
    }

    public void setPlayerGUI(PlayerGUI player){
        remove(this.player);
        this.player = player;
        add(player);
        repaint();
    }
}
