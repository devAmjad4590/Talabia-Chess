/**
 * The {@code Controller} class serves as the controller in the Model-View-Controller (MVC) architecture for the Talabia game.
 * It manages user input, updates the game model, and communicates with the game view.
 * Class implemented by Amgad Elrashid Gurashi Eltayeb.
 */

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import Model.*;
import Model.Movements.BackwardMovement;
import Model.Pieces.Piece;
import View.*;
import View.Components.*;

public class Controller {

    private GameView view; // the game view associated with this controller
    private Game model; // the game model associated with this controller
    private StartMenu startMenu;
    private Tile selectedTile, destinationTile; // the selected tile and the destination tile
    private SaveManager saveManager; // the save manager associated with this controller

    /**
     * Constructs a new Controller instance.
     *
     * @param view  The game view associated with this controller.
     * @param model The game model associated with this controller.
     */
    public Controller(GameView view, Game model) {
        this.view = view;
        this.model = model;
        this.saveManager = new SaveManager(model);
        initTileListeners();
        initButtonsListeners();
        showBoard();
    }

    /**
     * Constructs a new Controller instance.
     *
     * @param startMenu  The game start menu associated with this controller.
     * @param model The game model associated with this controller.
     */
    public Controller(Game model, StartMenu startMenu) {
        this.model = model;
        this.startMenu = startMenu;
        this.saveManager = new SaveManager(model);
        initMenuListeners();
    }

    /**
     * Initializes action listeners for all tiles on the game board.
     */
    private void initTileListeners() {
        view.getCenterPanel().setTilesActionListener(tileListener());
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

    /**
     * Shows all valid moves for the selected piece.
     */
    private void showMoves() {
        for (int i = 0; i < Board.getLength(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                if (model.getMove().isMoveValid(selectedTile, model.getTile(i, j))) {
                    view.getCenterPanel().getTileGUI(i, j).setAvailable(true);
                    // if the tile has an enemy piece
                    if (model.getTile(i, j).getPiece() != null
                            && model.getTile(i, j).getPiece().isYellow() != selectedTile.getPiece().isYellow()) {
                        view.getCenterPanel().getTileGUI(i, j).setEnemy(true);
                    }
                }
            }
        }
    }

    /**
     * Removes all valid moves from the game board.
     */
    private void removeMoves() {
        for (int i = 0; i < Board.getLength(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                view.getCenterPanel().getTileGUI(i, j).setAvailable(false);
                view.getCenterPanel().getTileGUI(i, j).setEnemy(false);
            }
        }
    }

    /**
     * Handles the player's move by updating the game model and refreshing the game
     * view.
     */
    private void handleMove() {
        if (model.getMove().isMoveValid(selectedTile, destinationTile)) {
            model.playerMove(selectedTile, destinationTile);
            view.playMoveSound();
            selectedTile = null;
            destinationTile = null;
            removeMoves();
            showBoard();
            if (model.getGameOver()) {
                handleGameOver();
            }

        } else {
            selectedTile = null;
            destinationTile = null;
            removeMoves();
        }
    }

    /**
     * Flips the pieces on the game board to reflect the current player's point of
     * view. Also shows the current state of the game board.
     */
    private void flipPieces() {
        clearImages();
        for (Map.Entry<Piece, Tile> entry : Board.getMap().entrySet()) {
            Piece piece = entry.getKey();
            Tile tile = entry.getValue();
            view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).flipImage(piece.toString());
            if (isBackward(tile.getX(), tile.getY())) {
                view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).setImage(piece.toString());
            }

        }
    }

    /**
     * Checks if the point piece on the given tile is moving backward.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return True if the piece on the given tile moving backward, false
     *         otherwise.
     */
    private boolean isBackward(int x, int y) {
        if (model.getTile(x, y).getPiece() == null)
            return false;
        return model.getTile(x, y).getPiece().getPieceMovement() instanceof BackwardMovement;
    }

    /**
     * Handles the end of the game by displaying the winner and asking the user if
     * they want to play again or quit the application.
     */
    void handleGameOver() {
        view.playWinMusic();
        view.getNorthPanel().showGameOver(model.getPlayerManager().getWinner().toString() + " wins!");
        int response = view.getNorthPanel().getResponse();
        if (response == 0) {
            view.closeWinMusic();
            clearImages();
            model.nextGame();
            showBoard();
        } else {
            System.exit(0);
        }
    }

    /**
     * Updates the game view to reflect the current state of the game board.
     */
    public void showBoard() {
        clearImages();
        for (Map.Entry<Piece, Tile> entry : Board.getMap().entrySet()) {
            Piece piece = entry.getKey();
            Tile tile = entry.getValue();
            view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).setImage(piece.toString());
            if (isBackward(tile.getX(), tile.getY())) {
                view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).flipImage(piece.toString());
            }
        }

        showPlayerScore();
        showSwapRound();
        flipPlayers(); // checks the current player and then flips to that current player's pov
    }

    
    public SaveManager getSaveManager() {
        return saveManager;
    }

    // removes all images from the game board
    private void clearImages() {
        view.getCenterPanel().clearImages();
    }

    // shows the number of moves left the swap piece round
    private void showSwapRound() {
        String turnsLeft = Integer.toString(4 - (model.getPlayerManager().getTurn() % 4)) + " Moves";
        view.getEastPanel().setSwapLabel(turnsLeft);
    }

    // updates the score of each player
    private void showPlayerScore() {
        view.getYellowPlayer().setText(model.getPlayerManager().getYellowPlayer().getScore());
        view.getBluePlayer().setText(model.getPlayerManager().getBluePlayer().getScore());
    }

    // flips the game board to reflect the current player's point of view
    private void flipPlayers() {
        if (model.getPlayerManager().getCurrentPlayer().isYellow()) {
            view.getCenterPanel().showLabels();
            view.getSouthPanel().setPlayerGUI(view.getYellowPlayer());
            view.getNorthPanel().setPlayerGUI(view.getBluePlayer());
        } else {
            flipPieces();
            view.getCenterPanel().reverseLabels();
            view.getSouthPanel().setPlayerGUI(view.getBluePlayer());
            view.getNorthPanel().setPlayerGUI(view.getYellowPlayer());
        }

    }

    public Game getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }

    public StartMenu getStartMenu() {
        return startMenu;
    }

    /**
     * Initializes action listeners for all buttons on the game view.
     */
    private void initButtonsListeners() {
        view.getNorthPanel().setNewGameActionListener(new ButtonActionListener(this).newGameListener());
        view.getNorthPanel().setQuitActionListener(new ButtonActionListener(this).quitActionListener());
        view.getEastPanel().setResignActionListener(new ButtonActionListener(this).resignActionListener());
        view.getNorthPanel().setSaveActionListener(new ButtonActionListener(this).saveActionListener());        
    }

    /**
     * Initializes action listeners for all buttons on the start menu.
     */
    private void initMenuListeners() {
        startMenu.setStartActionListener(new ButtonActionListener(this).startActionListener());
        startMenu.setLoadActionListener(new ButtonActionListener(this).loadActionListener());
        startMenu.setQuitActionListener(new ButtonActionListener(this).quitMenuListener());
    }

}
