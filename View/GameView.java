package View;

import View.Components.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The GameView class represents the main game interface of the Talabia Chess game.
 * It contains various panels for displaying the game components and handles sounds.
 * class by Asim Adel Ahmed Maroof
 */

public class GameView extends JFrame {

    private JFrame frame;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;
    private SouthPanel southPanel;
    private EastPanel eastPanel;
    private PlayerGUI player1, player2;
    private static Clip clip;
    
    /**
     * Constructor for GameView class.
     * Initializes the game interface components and sets up the frame.
     */
    public GameView() {
        frame = new JFrame("Talabia Chess Game");
        frame.setMinimumSize(new Dimension(850, 400));
        frame.setPreferredSize(new Dimension(850, 700));
        frame.setMaximumSize(new Dimension(850, 700));

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
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Retrieves the center panel of the game interface.
     * @return The center panel.
     */
    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * Retrieves the north panel of the game interface.
     * @return The north panel.
     */
    public NorthPanel getNorthPanel() {
        return northPanel;
    }

    /**
     * Retrieves the south panel of the game interface.
     * @return The south panel.
     */
    public SouthPanel getSouthPanel() {
        return southPanel;
    }

    /**
     * Retrieves the east panel of the game interface.
     * @return The east panel.
     */
    public EastPanel getEastPanel() {
        return eastPanel;
    }

    /**
     * Retrieves the player 1 (yellow player) interface.
     * @return The player 1 interface.
     */
    public PlayerGUI getYellowPlayer(){
        return player1;
    }

    /**
     * Retrieves the player 2 (blue player) interface.
     * @return The player 2 interface.
     */
    public PlayerGUI getBluePlayer(){
        return player2;
    }


    /**
     * Plays the win music when a player wins the game.
     */
    public void playWinMusic(){
        try{
            File file = new File("./View/sounds/WinMusic.wav"); // ill change it later
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Stops and closes the win music clip.
     */
    public void closeWinMusic(){
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

/**
     * Plays the move sound when a player makes a move.
     */
    public void playMoveSound(){
        try{
            File file = new File("./View/sounds/Move.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-0.0f);

            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    
    }}