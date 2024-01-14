package View;

import View.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameView extends JFrame {

    private JFrame frame;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;
    private SouthPanel southPanel;
    private EastPanel eastPanel;
    private PlayerGUI player1, player2;
    
    private int response;

    public GameView() {
        frame = new JFrame("Talabia Chess Game");
        frame.setMinimumSize(new Dimension(700, 400));

        player1 = new PlayerGUI("Player 1");
        player2 = new PlayerGUI("Player 2");
        centerPanel = new CenterPanel();
        northPanel = new NorthPanel(player2);
        southPanel = new SouthPanel(player1);
        eastPanel = new EastPanel();
        
        frame.setLayout(new BorderLayout());
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(eastPanel, BorderLayout.EAST);
        
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); 
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showGameOver(String winner){
    JOptionPane.showMessageDialog(frame, winner, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
    response = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
    }

    public void showSave(){
        JOptionPane.showMessageDialog(frame, "Game Saved!", "Save", JOptionPane.INFORMATION_MESSAGE);
    }

    public int getResponse(){
        return response;
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

    public EastPanel getEastPanel() {
        return eastPanel;
    }

    public PlayerGUI getYellowPlayer(){
        return player1;
    }

    public PlayerGUI getBluePlayer(){
        return player2;
    }
}