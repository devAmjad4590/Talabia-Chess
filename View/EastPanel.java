package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EastPanel extends JPanel{
    private JButton resignButton;

    public EastPanel() {
        setLayout(new GridBagLayout());

        resignButton = new JButton("Resign");
        resignButton.setPreferredSize(new Dimension(90, 90));

        // Create constraints to center the button
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Allows vertical centering

        // Add the button to the EAST position
        add(resignButton, gbc);
    }

    /**
     * Gets the button for resigning from the game.
     *
     * @return The button for resigning from the game.
     */
    public JButton getResignButton() {
        return resignButton;
    } 

}
