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
        initTileListeners();
        initButtonsListeners();
        showBoard();
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

    private void showMoves() {
        for (int i = 0; i < Board.getLength(); i++) {
            for (int j = 0; j < Board.getWidth(); j++) {
                if (model.getMove().isMoveValid(selectedTile, model.getTile(i, j))) {
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

    
    private void flipPieces() {
        clearImages();
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
        view.playWinMusic();
        view.getNorthPanel().showGameOver(model.getPlayerManager().getWinner().toString() + " wins!");
        int response =  view.getNorthPanel().getResponse();
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
        showSwapRound();
        flipPlayers(); // checks the current player and then flips to that current player's pov
    }

    private void clearImages(){
        view.getCenterPanel().clearImages();
    }

    private void showSwapRound(){
        String turnsLeft = Integer.toString(4 - (model.getPlayerManager().getTurn() % 4)) + " Moves";
        view.getEastPanel().setSwapLabel(turnsLeft);
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
                view.getNorthPanel().showNewGame();
                int response =  view.getNorthPanel().getResponse();
                if(response == 0){
                    model.init();
                    showBoard();
                }
            }
        };
    }

    private ActionListener resignActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getEastPanel().showResign();
                int response =  view.getEastPanel().getResponse();
                if(response == 0){
                    model.getPlayerManager().resign();
                    handleGameOver();
                }
            }
        };
    }

    private ActionListener quitActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getNorthPanel().showQuit();
                int response =  view.getNorthPanel().getResponse();
                if(response == 0){
                    System.exit(0);
                }
            }
        };
    }

    private ActionListener saveActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getNorthPanel().showSave();
                int response =  view.getNorthPanel().getResponse();
                if(response == 0){
                    saveManager.saveGame();
                    view.getNorthPanel().showSuccessSave();
                }               
            }
        };
    }

    private void loadGame(){
        saveManager.loadGame();
        showBoard();
    }

    private void initButtonsListeners() {
        view.getNorthPanel().setNewGameActionListener(newGameListener());
        view.getNorthPanel().setQuitActionListener(quitActionListener());
        view.getEastPanel().setResignActionListener(resignActionListener());
        view.getNorthPanel().setSaveActionListener(saveActionListener());
    }

}
