import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JFrame {
    // Create a 7x6 array of buttons for the game board
    private JButton[][] buttons = new JButton[7][6];
    // Labels for the scores of player 1 and player 2
    private JLabel player1Score, player2Score;

    public GameBoard() {
        // Set the layout manager for the JFrame
        setLayout(new BorderLayout());
        // Create a panel for the game board with a 7x6 grid layout
        JPanel board = new JPanel(new GridLayout(7, 6));
        // Initialize the buttons and add them to the game board
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton();
                board.add(buttons[i][j]);
            }
        }
        // Add the game board to the center of the JFrame
        add(board, BorderLayout.CENTER);

        // Create a panel for the scores with a 3x2 grid layout
        JPanel scorePanel = new JPanel(new GridLayout(3, 2));
        // Add labels and score labels for player 1 and player 2
        scorePanel.add(new JLabel("Player 1"));
        player1Score = new JLabel("####");
        scorePanel.add(player1Score);
        scorePanel.add(new JLabel("Player 2"));
        player2Score = new JLabel("####");
        scorePanel.add(player2Score);
        // Add the score panel to the west (left) of the JFrame
        add(scorePanel, BorderLayout.WEST);

        // Create a panel for the control buttons with a 3x1 grid layout
        JPanel controlPanel = new JPanel(new GridLayout(3, 1));
        // Add the "Save", "Quit", and "Exit" buttons
        controlPanel.add(new JButton("Save"));
        controlPanel.add(new JButton("Load"));
        controlPanel.add(new JButton("Exit"));
        // Add the control panel to the east (right) of the JFrame
        add(controlPanel, BorderLayout.EAST);

        // Set the default close operation to exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Pack the components in the JFrame
        pack();
        // Make the JFrame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create a new instance of the game
        new GameBoard();
    }
}
