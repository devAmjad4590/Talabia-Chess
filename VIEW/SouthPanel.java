package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import View.Components.PlayerGUI;

/**
 * The {@code SouthPanel} class represents a Swing JPanel that serves as the bottom panel
 * in a graphical user interface, typically used for displaying player 1 information and control buttons.
 * It contains a PlayerGUI instance for player 1 and buttons for actions like New, Save, Quit, and Resign.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public class SouthPanel extends JPanel {
    private JButton newButton, saveButton, quitButton, resignButton;
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

        // Create a subpanel with FlowLayout for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Create buttons and add them to the subpanel
        newButton = new JButton("New");
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");
        resignButton = new JButton("Resign");

        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(resignButton);

        // Add the subpanel to the SOUTH region
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Gets the button for initiating a new game.
     *
     * @return The button for initiating a new game.
     */
    public JButton getNewButton() {
        return newButton;
    }

    /**
     * Gets the button for saving the game.
     *
     * @return The button for saving the game.
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Gets the button for quitting the game.
     *
     * @return The button for quitting the game.
     */
    public JButton getQuitButton() {
        return quitButton;
    }

    /**
     * Gets the button for resigning from the game.
     *
     * @return The button for resigning from the game.
     */
    public JButton getResignButton() {
        return resignButton;
    }

    public void setPlayerGUI(PlayerGUI player){
        remove(this.player);
        this.player = player;
        add(player);
        repaint();
    }
}
