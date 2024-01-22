package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class startMenue extends JFrame {
    private JButton startGameButton, openGameButton, closeGameButton;
    private ImageIcon gifIcon;
    private JLabel gifLabel;

    public startMenue() {
        setMinimumSize(new Dimension(300, 300));

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        gifIcon = new ImageIcon("View/Images/Palestine_animated-flag-gifs.gif");
        gifLabel = new JLabel("", gifIcon, JLabel.CENTER);
        gifLabel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(gifLabel, Integer.valueOf(1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBounds(150, 250, 200, 150);
        buttonPanel.setOpaque(false);
        layeredPane.add(buttonPanel, Integer.valueOf(2));

        startGameButton = new JButton("Start Game");
        openGameButton = new JButton("Open Load Game");
        closeGameButton = new JButton("Quit Game");

        // startGameButton.setPreferredSize(new Dimension(150, 40));
        // openGameButton.setPreferredSize(new Dimension(150, 40));
        // closeGameButton.setPreferredSize(new Dimension(150, 40));

        startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        getContentPane().add(layeredPane);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(startGameButton);
        buttonPanel.add(openGameButton);
        buttonPanel.add(closeGameButton);
        buttonPanel.add(Box.createVerticalGlue());

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                buttonPanel.setBounds(0, 0, getWidth(), getHeight());
                gifLabel.setBounds(0, 0, getWidth(), getHeight());
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame
                dispose();

                // To Do: open a new game

            }
        });

        openGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // To Do: load a saved game
            }
        });

        closeGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menue Page");
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new startMenue();
    }
}