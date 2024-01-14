package View.Components;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code PlayerGUI} class represents a Swing JPanel that displays information about a player
 * in a graphical user interface. It includes a JLabel for the player's name and win/lose statistics.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public class PlayerGUI extends JPanel {
    private JLabel player; // The JLabel displaying the player's name and win/lose statistics.
    private String playerName;
    private JLabel imageLabel;
    private String imagePath = "./View/images/blank-profile.png";
    private int wins = 0, loses = 0; // The number of wins/loses for the player


    /**
     * Constructs a new PlayerGUI instance.
     * Initializes the panel with a JLabel for displaying the player's name and win/lose statistics.
     *
     * @param playerName The name of the player.
     */
    public PlayerGUI(String player){
        setLayout(new FlowLayout());
        this.playerName = player;
        this.player = new JLabel(player + " (" + wins + " - " + loses + ")");
        imageLabel = new JLabel();
        setImage();
        add(imageLabel);
        add(this.player);
    }

    public void setImage() {
            try {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
                
                imageIcon = new ImageIcon(image);
                imageLabel.setIcon(imageIcon);
    
                // centering the image
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setVerticalAlignment(JLabel.CENTER);
            } catch (Exception e) {
                e.printStackTrace(); // Print the exception for debugging
            }
    }
    

    public JLabel getLabel(){
        return player;
    }

    public void setText(String text){
        player.setText(playerName + " " + text);
    }

    
}
