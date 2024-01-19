package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EastPanel extends JPanel {
    private JButton resignButton;
    private JFrame frame;
    private int response;
    private JLabel swapLabel;

    public EastPanel() {
        frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        setLayout(new GridBagLayout());

        resignButton = new JButton("Resign");
        resignButton.setPreferredSize(new Dimension(90, 90));

        swapLabel = new JLabel("Swap round in: ");
        swapLabel.setFont(new Font("Serif", Font.BOLD, 20));
        // Create constraints to center the button
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Allows vertical centering

        // add margin to the button
        gbc.insets = new Insets(0, 30, 0, 10);

        // Add the button to the EAST position
        add(swapLabel, gbc);
        gbc.gridy = 1;
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

    public void setSwapLabel(String text) {
        swapLabel.setText("Swap round in: " + text);
    }

    public void setResignActionListener(ActionListener listener) {
        resignButton.addActionListener(listener);
    }

    public void showResign() {
        response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to resign?", "Resign",
                JOptionPane.YES_NO_OPTION);
    }

    public int getResponse() {
        return response;
    }

}
