package VIEW;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code CenterPanel} class represents a Swing JPanel that serves as the central component
 * for displaying a chessboard in a graphical user interface.
 * It contains a 7x6 grid of TileGUI instances, each representing a visual tile on the chessboard.
 * The layout is organized using GridLayout.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public class CenterPanel extends JPanel {

    /**
     * A 2D array of TileGUI instances representing the visual representation of tiles on the chessboard.
     * Each element in the array corresponds to a specific position on the chessboard.
     */
    private TileGUI[][] tileGUIs = new TileGUI[7][6];


     /**
     * Constructs a new CenterPanel instance.
     * Initializes the layout manager to GridLayout with 7 rows and 6 columns.
     * Populates the panel with TileGUI instances, each representing a visual tile on the chessboard.
     */
    public CenterPanel() {
        setLayout(new GridLayout(7, 6));

        // Populate the panel with TileGUI instances
        for (int i =0; i < 7; i++){
            for (int j = 0; j < 6; j++){
                tileGUIs[i][j] = new TileGUI(i, j);
                add(tileGUIs[i][j]);
            }
        }
    }
}
