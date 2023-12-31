package VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

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
    private TileGUI[][] tileGUIs = new TileGUI[6][7];


     /**
     * Constructs a new CenterPanel instance.
     * Initializes the layout manager to GridLayout with 7 rows and 6 columns.
     * Populates the panel with TileGUI instances, each representing a visual tile on the chessboard.
     */
    public CenterPanel() {
        setLayout(new GridLayout(6, 7));

        // Populate the panel with TileGUI instances
        for (int i =0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                tileGUIs[i][j] = new TileGUI(i, j);
                add(tileGUIs[i][j]);
            }
        }
    }

    /**
     * Gets the TileGUI instance at the specified x and y coordinates.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The TileGUI instance at the specified x and y coordinates.
     */
    public TileGUI getTileGUI(int x, int y){
        return tileGUIs[x][y];
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);        
    }
}
