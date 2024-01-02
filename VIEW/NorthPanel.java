package View;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import View.Components.PlayerGUI;

/**
 * The {@code NorthPanel} class represents a Swing JPanel that serves as the top panel
 * in a graphical user interface, typically used for displaying player information.
 * It contains a PlayerGUI instance representing player 2.
 * Class implemented by Asim Adel Ahmed Maroof
 */

public class NorthPanel extends JPanel{
    private PlayerGUI player2; // The PlayerGUI instance representing player 2.
    private int wins, loses;

    /**
     * Constructs a new NorthPanel instance.
     * Initializes the panel with a PlayerGUI instance representing player 2.
     *
     * @param player2 The PlayerGUI instance representing player 2.
     */
    public NorthPanel(PlayerGUI player2) {
        setLayout(new BorderLayout());
        this.player2 = player2; 
        add(player2);
    }

    /**
     * Gets the number of wins for player 2.
     *
     * @return The number of wins for player 2.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Gets the number of losses for player 2.
     *
     * @return The number of losses for player 2.
     */
    public int getLoses() {
        return loses;
    }

}
