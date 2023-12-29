package VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code TileGUI} class represents a Swing JPanel that visually represents a tile on a chessboard
 * in a graphical user interface. It includes methods to retrieve the x and y coordinates of the tile.
 * The appearance of the tile is determined by alternating black and white colors based on its coordinates.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public class TileGUI extends JPanel {
    private int x;
    private int y;

    /**
     * Constructs a new TileGUI instance.
     * Initializes the tile with the specified x and y coordinates and sets its preferred size.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     */
    public TileGUI(int x, int y) {
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(80, 80));
        // set maximum size to preferred size to prevent stretching
        setMaximumSize(new Dimension(90, 90));
    }

    /**
     * Gets the x-coordinate of the tile.
     *
     * @return The x-coordinate of the tile.
     */
    public int getTileGUIX(){
        return x;
    }

    /**
     * Gets the y-coordinate of the tile.
     *
     * @return The y-coordinate of the tile.
     */
    public int getTileGUIY(){
        return y;
    }

    /**
     * Overrides the paintComponent method to customize the appearance of the tile.
     * Alternates the background color of the tile between black and white based on its coordinates.
     *
     * @param g The Graphics context used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground((x + y) % 2 == 0 ? Color.BLACK : Color.WHITE);
    }
}
