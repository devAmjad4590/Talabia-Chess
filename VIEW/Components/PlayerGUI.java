package View.Components;

import java.awt.Component;

import javax.swing.*;

/**
 * The {@code PlayerGUI} class represents a Swing JPanel that displays information about a player
 * in a graphical user interface. It includes a JLabel for the player's name and win/lose statistics.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public class PlayerGUI extends JPanel {
    private JLabel player; // The JLabel displaying the player's name and win/lose statistics.
    private int wins =0, loses = 0; // The number of wins/loses for the player


    /**
     * Constructs a new PlayerGUI instance.
     * Initializes the panel with a JLabel for displaying the player's name and win/lose statistics.
     *
     * @param playerName The name of the player.
     */
    public PlayerGUI(String player){
        this.player = new JLabel(player + " (" + wins + " - " + loses + ")");
        add(this.player);

    }

    public JLabel getLabel(){
        return player;
    }

    /**
     * Gets the number of wins for the player.
     *
     * @return The number of wins for the player.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Gets the number of losses for the player.
     *
     * @return The number of losses for the player.
     */
    public int getLoses() {
        return loses;
    }

    /**
     * Increments the number of wins for the player.
     */
    public void setWin(){
        wins++;
    }

    /**
     * Increments the number of losses for the player.
     */
    public void setLose(){
        loses++;
    }

    /**
     * Resets the win and lose statistics for the player.
     */
    public void reset(){
        wins = 0;
        loses = 0;
    }

    
}
