package View;

import View.Components.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class GameView extends JFrame {

    private JFrame frame;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;
    private SouthPanel southPanel;
    private EastPanel eastPanel;
    private PlayerGUI player1, player2;
    private static Clip clip;
    
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

    public EastPanel getEastPanel() {
        return eastPanel;
    }

    public PlayerGUI getYellowPlayer(){
        return player1;
    }

    public PlayerGUI getBluePlayer(){
        return player2;
    }


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

    public void closeWinMusic(){
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

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