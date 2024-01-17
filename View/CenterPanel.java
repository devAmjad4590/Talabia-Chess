package View;

import View.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        showLabels();
    }

    public void showLabels(){
        // Add row labels (numbers) to the WEST position
        JPanel rowLabelNumbers = new JPanel(new GridLayout(6, 1));
        rowLabelNumbers.setPreferredSize(new Dimension(30, 50));
        rowLabelNumbers.setBackground(Color.LIGHT_GRAY);
        for(int i = 0; i < 6; i++){
            JLabel rowLabels = new JLabel(String.valueOf(i + 1), JLabel.CENTER);
            rowLabelNumbers.add(rowLabels);
        }
        add(rowLabelNumbers, BorderLayout.WEST);

        // Add column labels (letters) to the NORTH position
        JPanel colLabelLetters = new JPanel(new GridLayout(1, 7));
        colLabelLetters.setPreferredSize(new Dimension(10, 30));
        colLabelLetters.setBackground(Color.LIGHT_GRAY);
        for(char c = 'A'; c <= 'G'; c++) {
            JLabel colLabels = new JLabel(String.valueOf(c), JLabel.CENTER);
            colLabelLetters.add(colLabels);
        }
        add(colLabelLetters, BorderLayout.NORTH);
    }

    public void reverseLabels(){

        // Add row labels (numbers) to the WEST position
        JPanel rowLabelNumbers = new JPanel(new GridLayout(6, 1));
        rowLabelNumbers.setPreferredSize(new Dimension(30, 50));
        rowLabelNumbers.setBackground(Color.LIGHT_GRAY);
        for(int i = 6; i >= 1; i--){
            JLabel rowLabels = new JLabel(String.valueOf(i), JLabel.CENTER);
            rowLabelNumbers.add(rowLabels);
        }
        add(rowLabelNumbers, BorderLayout.WEST);

        // Add column labels (letters) to the NORTH position
        JPanel colLabelLetters = new JPanel(new GridLayout(1, 7));
        colLabelLetters.setPreferredSize(new Dimension(10, 30));
        colLabelLetters.setBackground(Color.LIGHT_GRAY);
        for(char c = 'G'; c >= 'A'; c--) {
            JLabel colLabels = new JLabel(String.valueOf(c), JLabel.CENTER);
            colLabelLetters.add(colLabels);
        }
        add(colLabelLetters, BorderLayout.NORTH);
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

    public void setTilesActionListener(ActionListener actionListener){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                tileGUIs[i][j].addActionListener(actionListener);
            }
        }
    }

    public void clearImages(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                tileGUIs[i][j].setImage("");
            }
        }
    }
    

}
