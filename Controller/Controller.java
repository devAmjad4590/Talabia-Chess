/**
 * The {@code Controller} class serves as the controller in the Model-View-Controller (MVC) architecture for the Talabia game.
 * It manages user input, updates the game model, and communicates with the game view.
 * Class implemented by Amgad Elrashid Gurashi Eltayeb.
 */

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Game;
import Model.Tile;
import View.GameView;
import View.Components.TileGUI;

public class Controller {

    private GameView view;
    private Game model;
    private Tile selectedTile, destinationTile;

    /**
     * Constructs a new Controller instance.
     *
     * @param view  The game view associated with this controller.
     * @param model The game model associated with this controller.
     */
    public Controller(GameView view, Game model) {
        this.view = view;
        this.model = model;
        showBoard();
        initTileListeners();
    }

    /**
     * Initializes action listeners for all tiles on the game board.
     */
    private void initTileListeners() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
                view.getCenterPanel().getTileGUI(i, j).addActionListener(tileListener());
            }
        }
    }

    /**
     * Creates an action listener for a tile button on the game board.
     *
     * @return The ActionListener instance.
     */
    private ActionListener tileListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TileGUI tileGUI = (TileGUI) e.getSource();
                if (selectedTile == null) {
                    tileGUI.setClicked(true);
                    selectedTile = model.getBoard().getTile(tileGUI.getTileX(), tileGUI.getTileY());
                } else {
                    view.getCenterPanel().getTileGUI(selectedTile.getX(), selectedTile.getY()).setClicked(false);
                    destinationTile = model.getBoard().getTile(tileGUI.getTileX(), tileGUI.getTileY());
                    handleMove();
                }
            }
        };
    }

    /**
     * Handles the player's move by updating the game model and refreshing the game view.
     */
    private void handleMove() {
        model.setPlayerMove(model.getCurrentPlayer(), selectedTile, destinationTile);
        selectedTile = null;
        destinationTile = null;
        showBoard();
    }

    /**
     * Updates the game view to reflect the current state of the game board.
     */
    private void showBoard() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
                if (model.getTile(i, j).getPiece() != null) {
                    view.getCenterPanel().getTileGUI(i, j).setImage(model.getTile(i, j).getPiece().toString());
                } else {
                    view.getCenterPanel().getTileGUI(i, j).setImage(null);
                }
            }
        }
    }
}
