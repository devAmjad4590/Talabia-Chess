package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import View.Components.*;

/**
 * The {@code NorthPanel} class represents a Swing JPanel that serves as the top panel
 * in a graphical user interface, typically used for displaying player information.
 * It contains a PlayerGUI instance representing player 2.
 * Class implemented by Asim Adel Ahmed Maroof
 */

public class NorthPanel extends JPanel{
   private PlayerGUI player;
   private JButton newGameButton, saveButton, quitButton;

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
     * Gets the button for initiating a new game.
     *
     * @return The button for initiating a new game.
     */
    public JButton getNewGameButton(){
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


    
    public void setPlayerGUI(PlayerGUI player){
        remove(this.player);
        this.player = player;
        add(this.player);
        repaint();
    }

}
