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
import View.GameView;
import View.Components.*;

public class Controller {

    private GameView view;
    private Game model;
    private Tile selectedTile, destinationTile;
    private SaveManager saveManager;

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
                    // if the tile has an enemy piece
                    if(model.getTile(i, j).getPiece() != null && model.getTile(i, j).getPiece().isYellow() != selectedTile.getPiece().isYellow()){
                        view.getCenterPanel().getTileGUI(i, j).setEnemy(true);
                    }
                }
            }
        }
    }



    private void removeMoves() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
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
        if (model.isMoveValid(selectedTile, destinationTile)) {
            model.setPlayerMove(selectedTile, destinationTile);
            selectedTile = null;
            destinationTile = null;
            removeMoves();
            showBoard();
            flipPlayers();
            if (model.isGameOver()) {
                handleGameOver();
            }

        } else {
            selectedTile = null;
            destinationTile = null;
            removeMoves();
        }
    }

    private void flipPieces() {
        for(Map.Entry<Piece, Tile> entry: Board.getMap().entrySet()){    
            Piece piece = entry.getKey();
            Tile tile = entry.getValue();
                view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).flipImage(piece.toString());
                if(isBackward(tile.getX(), tile.getY())){
                    view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).setImage(piece.toString());
                }
            
        }
    }

    private boolean isBackward(int i, int j){
        if(model.getTile(i, j).getPiece() == null) return false;
        return model.getTile(i, j).getPiece().getPieceMovement() instanceof BackwardMovement;
    }

    private void handleGameOver() {
        view.showGameOver(model.getPlayerManager().getWinner().toString() + " wins!");
        int response = view.getResponse();
        if (response == 0) {
            clearImages();
            model.nextGame();
            showBoard();
        } else {
            quit();
        }
    }

    /**
     * Updates the game view to reflect the current state of the game board.
     */
    private void showBoard() {
        clearImages();
        for(Map.Entry<Piece, Tile> entry : Board.getMap().entrySet()){
            Piece piece = entry.getKey();
            Tile tile = entry.getValue();
            view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).setImage(piece.toString());
            if(isBackward(tile.getX(), tile.getY())){
                view.getCenterPanel().getTileGUI(tile.getX(), tile.getY()).flipImage(piece.toString());
            }
        }

        showPlayerScore();
        flipPlayers(); // checks the current player and then flips to that current player's pov
    }

    private void clearImages(){
        for(int i = 0; i < model.getBoard().getLength(); i++){
            for(int j = 0; j < model.getBoard().getWidth(); j++){
                view.getCenterPanel().getTileGUI(i, j).setImage("");
            }
        }
    }


    private void showPlayerScore() {
        view.getYellowPlayer().setText(model.getPlayerManager().getYellowPlayer().getScore());
        view.getBluePlayer().setText(model.getPlayerManager().getBluePlayer().getScore());
    }

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

    private ActionListener saveActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showSave();
                saveManager.saveGame();
            }
        };
    }

    private void loadGame(){
        saveManager.loadGame();
        model.setCurrentPlayer();
        showBoard();
    }

    private void quit() {
        System.exit(0);
    }

    private void initButtonsListeners() {
        view.getNorthPanel().getNewGameButton().addActionListener(newGameListener());
        view.getNorthPanel().getQuitButton().addActionListener(quitActionListener());
        view.getEastPanel().getResignButton().addActionListener(resignActionListener());
        view.getNorthPanel().getSaveButton().addActionListener(saveActionListener());
    }

}
