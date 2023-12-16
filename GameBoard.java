import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JFrame {
    private JButton[][] buttons = new JButton[7][6];
    private JLabel player1Score, player2Score;

    public GameBoard() {
        setLayout(new BorderLayout());
        JPanel board = new JPanel(new GridLayout(7, 6));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new JButton();
                board.add(buttons[i][j]);
            }
        }
        add(board, BorderLayout.CENTER);

        JPanel scorePanel = new JPanel(new GridLayout(3, 2));
        scorePanel.add(new JLabel("Player 1"));
        player1Score = new JLabel("####");
        scorePanel.add(player1Score);
        scorePanel.add(new JLabel("Player 2"));
        player2Score = new JLabel("####");
        scorePanel.add(player2Score);
        add(scorePanel, BorderLayout.WEST);

        JPanel controlPanel = new JPanel(new GridLayout(3, 1));
        controlPanel.add(new JButton("Save"));
        controlPanel.add(new JButton("Quit"));
        controlPanel.add(new JButton("Exit"));
        add(controlPanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameBoard();
    }
}
