package VIEW;

import javax.swing.*;

import VIEW.Components.*;

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
    private TileGUI[][] tileGUIs = new TileGUI[6][7];


     /**
     * Constructs a new CenterPanel instance.
     * Initializes the layout manager to GridLayout with 7 rows and 6 columns.
     * Populates the panel with TileGUI instances, each representing a visual tile on the chessboard.
     */
    public CenterPanel() {
        setLayout(new BorderLayout());

        // Create a subpanel for the grid (CENTER position)
        JPanel gridPanel = new JPanel(new GridLayout(6, 7));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tileGUIs[i][j] = new TileGUI(i, j);
                gridPanel.add(tileGUIs[i][j]);
            }
        }

        // Add the gridPanel to the CENTER position
        add(gridPanel, BorderLayout.CENTER);

        // Add row labels (numbers) to the WEST position
        JPanel rowLabelPanel = new JPanel(new GridLayout(6, 1));
        rowLabelPanel.setPreferredSize(new Dimension(50, 90));
        rowLabelPanel.setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < 6; i++) {
            JLabel rowLabel = new JLabel(String.valueOf(i + 1), JLabel.CENTER);
            // rowLabel.setFont(rowLabel.getFont().deriveFont(Font.BOLD, 30));
            rowLabelPanel.add(rowLabel);
        }
        add(rowLabelPanel, BorderLayout.WEST);

        // Add column labels (letters) to the NORTH position
        JPanel colLabelPanel = new JPanel(new GridLayout(1, 7));
        colLabelPanel.setPreferredSize(new Dimension(50, 90));
        colLabelPanel.setBackground(Color.LIGHT_GRAY);
        for (char c = 'A'; c <= 'G'; c++) {
            JLabel colLabel = new JLabel(String.valueOf(c), JLabel.CENTER);
            colLabelPanel.add(colLabel);
            // colLabel.setFont(colLabel.getFont().deriveFont(Font.BOLD, 30));
        }
        add(colLabelPanel, BorderLayout.NORTH);

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
    

}
