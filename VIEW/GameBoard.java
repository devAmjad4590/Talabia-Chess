package VIEW;

import Model.Tile;
import VIEW.Components.PlayerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameBoard extends JFrame {

    private JFrame frame;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;
    private SouthPanel southPanel;
    private PlayerGUI player1, player2;

    public GameBoard() {
        frame = new JFrame("Talabia Chess Game");
        frame.setMinimumSize(new Dimension(700, 400));

        player1 = new PlayerGUI("Player 1");
        player2 = new PlayerGUI("Player 2");
        centerPanel = new CenterPanel();
        northPanel = new NorthPanel(player2);
        southPanel = new SouthPanel(player1);
        
        
        frame.setLayout(new BorderLayout());
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); 
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public NorthPanel getNorthPanel() {
        return northPanel;
    }

    public SouthPanel getSouthPanel() {
        return southPanel;
    }
    public static void main(String[] args){
        new GameBoard();
    }
}