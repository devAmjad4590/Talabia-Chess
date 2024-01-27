package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import View.Components.*;

/**
 * The {@code NorthPanel} class represents a Swing JPanel that serves as the top
 * panel
 * in a graphical user interface, typically used for displaying player
 * information.
 * It contains a PlayerGUI instance representing player 2.
 * Class implemented by Asim Adel Ahmed Maroof
 */

public class NorthPanel extends JPanel {
    private PlayerGUI player;
    private JButton newGameButton, saveButton, quitButton;
    private int response;
    private JFrame frame;

    /**
     * Constructs a new NorthPanel instance.
     * Initializes the panel with a PlayerGUI instance representing player 2.
     *
     * @param player2 The PlayerGUI instance representing player 2.
     */
    public NorthPanel(PlayerGUI player) {
        frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        setLayout(new BorderLayout());
        this.player = player;
        add(this.player);


        

        // Create a panel for the toolBar
        JPanel toolBarPanel = new JPanel(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        newGameButton = new JButton("New");
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");

        toolBar.add(newGameButton);
        toolBar.add(saveButton);
        toolBar.add(quitButton);

        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBarPanel.add(toolBar, BorderLayout.PAGE_START);

        // Add the toolBarPanel to the frame
        add(toolBarPanel, BorderLayout.PAGE_START);
    }

   /**
     * Displays a dialog indicating the end of the game and the winner.
     * @param winner The winner of the game.
     */
    public void showGameOver(String winner) {
        JOptionPane.showMessageDialog(frame, winner, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        response = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Play Again",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * Displays a dialog asking the user if they want to save the game.
     */
    public void showSave() {
        response = JOptionPane.showConfirmDialog(frame, "Do you want to save this game?", "Save",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * Displays a dialog indicating that the game was saved successfully.
     */
    public void showSuccessSave() {
        JOptionPane.showMessageDialog(frame, "Game saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gets the user's response from the dialog.
     * @return The user's response (YES_OPTION or NO_OPTION).
     */
    public int getResponse() {
        return response;
    }

    /**
     * Displays a dialog asking the user if they want to start a new game.
     */
    public void showNewGame() {
        response = JOptionPane.showConfirmDialog(frame, "Do you want to start a new game?", "New Game",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * Displays a dialog asking the user if they want to quit the game.
     */
    public void showQuit() {
        response = JOptionPane.showConfirmDialog(frame, "Do you want to quit the game?", "Quit",
                JOptionPane.YES_NO_OPTION);
    }

    /**
     * Gets the button for initiating a new game.
     *
     * @return The button for initiating a new game.
     */
    public JButton getNewGameButton() {
        return newGameButton;
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
     * Sets the action listener for the new game button.
     *
     * @param listener The action listener for the new game button.
     */
    public void setNewGameActionListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the save button.
     *
     * @param listener The action listener for the save button.
     */
    public void setSaveActionListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    /**
     * Sets the action listener for the quit button.
     *
     * @param listener The action listener for the quit button.
     */
    public void setQuitActionListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }

    public void setPlayerGUI(PlayerGUI player) {
        remove(this.player);
        this.player = player;
        add(this.player);
        repaint();
    }

}
