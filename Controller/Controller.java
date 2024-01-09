/**
 * The {@code Controller} class serves as the controller in the Model-View-Controller (MVC) architecture for the Talabia game.
 * It manages user input, updates the game model, and communicates with the game view.
 * Class implemented by Amgad Elrashid Gurashi Eltayeb.
 */

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import VIEW.GameView;
import VIEW.Components.*;

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
        initButtonsListeners();
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
                    selectedTile = model.getTile(tileGUI.getTileX(), tileGUI.getTileY());
                    showMoves();
                } else {
                    destinationTile = model.getTile(tileGUI.getTileX(), tileGUI.getTileY());
                    handleMove();
                }
            }
        };
    }

    private void showMoves() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
                if (model.isMoveValid(selectedTile, model.getTile(i, j))) {
                    view.getCenterPanel().getTileGUI(i, j).setAvailable(true);
                }
            }
        }
    }

    private void removeMoves() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
                view.getCenterPanel().getTileGUI(i, j).setAvailable(false);
            }
        }
    }

    /**
     * Handles the player's move by updating the game model and refreshing the game
     * view.
     */
    private void handleMove() {
        model.setPlayerMove(selectedTile, destinationTile);
        selectedTile = null;
        destinationTile = null;
        removeMoves();
        flipPlayerGUI();
        showBoard();
        if (model.isGameOver()) {
            handleGameOver();
        }
    }

    private void handleGameOver() {
        view.showGameOver(model.getPlayerManager().getWinner().toString() + " wins!");
        int response = view.getResponse();
        if (response == 0) {
            model.nextGame();
            flipPlayerGUI(); // flip back to original position
            showBoard();
        } else {
            quit();
        }
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
                    view.getCenterPanel().getTileGUI(i, j).setImage("");
                }
            }
        }
        // setting the player's score
        showPlayerScore();
    }

    private void showPlayerScore(){
        view.getYellowPlayer().setText(model.getPlayerManager().getYellowPlayer().getScore());
        view.getBluePlayer().setText(model.getPlayerManager().getBluePlayer().getScore());
    }

    private void flipPlayerGUI() {
        if (model.getPlayerManager().getCurrentPlayer().isYellow()) {
            view.getSouthPanel().setPlayerGUI(view.getYellowPlayer());
            view.getNorthPanel().setPlayerGUI(view.getBluePlayer());
        } else {
            view.getSouthPanel().setPlayerGUI(view.getBluePlayer());
            view.getNorthPanel().setPlayerGUI(view.getYellowPlayer());
        }

    }

    private ActionListener newGameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.resetAll();
                showBoard();
            }
        };
    }

    private ActionListener resignActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.resign();
                handleGameOver();
            }
        };
    }

    private ActionListener quitActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
    }

    private void quit() {
        System.exit(0);
    }

    private void initButtonsListeners() {
        view.getSouthPanel().getNewButton().addActionListener(newGameListener());
        view.getSouthPanel().getQuitButton().addActionListener(quitActionListener());
        view.getSouthPanel().getResignButton().addActionListener(resignActionListener());
    }

}