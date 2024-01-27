package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

/**
 * The StartMenu class represents the main menu of the Talabia Chess game.
 * It provides options to start a new game, load a saved game, or exit the game.
 * by Asim Adel Ahmed Maroof
 */
public class StartMenu extends JFrame {
    private JButton startButton, loadButton, exitButton;
    private JPanel panel;
    private JLabel title;
    private int buttonWidth = 200;
    private int buttonHeight = 50;
    private int spacing = 40;
    private static Clip clip;


    /**
     * Constructor for StartMenu class.
     * Initializes the main menu UI components and plays background music.
     */
    public StartMenu() {
        super("Talabia Chess");
        playBackgroundMusic();
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        title = new JLabel("Talabia Chess", SwingConstants.CENTER);
        title.setFont(new Font("Monospace", Font.BOLD, 20));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, spacing + 20, 0);
        panel.add(title, gbc);

        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        loadButton = new JButton("Load Game");
        loadButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        exitButton = new JButton("Exit Game");
        exitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(spacing, 0, 0, 0);

        panel.add(startButton, gbc);
        panel.add(loadButton, gbc);
        panel.add(exitButton, gbc);

        add(panel);

        setResizable(false);
        setVisible(true);
    }

    /**
     * Getter for the startButton.
     * @return The startButton JButton object.
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Getter for the loadButton.
     * @return The loadButton JButton object.
     */
    public JButton getLoadButton() {
        return loadButton;
    }

    /**
     * Getter for the exitButton.
     * @return The exitButton JButton object.
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Setter for the startButton ActionListener.
     * @param actionListener The ActionListener to be set.
     */
    public void setStartActionListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

    /**
     * Setter for the loadButton ActionListener.
     * @param actionListener The ActionListener to be set.
     */
    public void setLoadActionListener(ActionListener actionListener) {
        loadButton.addActionListener(actionListener);
    }

    /**
     * Setter for the exitButton ActionListener.
     * @param actionListener The ActionListener to be set.
     */
    public void setQuitActionListener(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }

    /**
     * Plays the background music for the main menu and for the rest of the game.
     */
    private static void playBackgroundMusic(){
        try{
            File file = new File("./View/sounds/BackgroundMusic.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
