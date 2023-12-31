package Controller;

import java.awt.event.*;

import Model.*;
import VIEW.*;
import VIEW.Components.TileGUI;

public class Controller {
    private GameBoard view;
    private Game model;
    private Tile selectedTile, destinationTile;

    public Controller(GameBoard view, Game model) {
        this.view = view;
        this.model = model;
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
                if (model.getTile(i, j).getPiece() != null) {
                    view.getCenterPanel().getTileGUI(i, j).setImage(model.getTile(i, j).getPiece().toString() + ".png");
                }
                view.getCenterPanel().getTileGUI(i, j).addMouseListener(tileListener());
            }
        }
    }

    private MouseAdapter tileListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TileGUI tileGUI = (TileGUI) e.getSource();
                if (selectedTile == null) {
                    tileGUI.setClicked(true);
                    selectedTile = model.getBoard().getTile(tileGUI.getTileGUIX(), tileGUI.getTileGUIY());
                } else {
                    view.getCenterPanel().getTileGUI(selectedTile.getX(), selectedTile.getY()).setClicked(false);
                    destinationTile = model.getBoard().getTile(tileGUI.getTileGUIX(), tileGUI.getTileGUIY());
                    handleMove();
                }
            }
        };
    }

    private void handleMove() {
        model.setPlayerMove(model.getPlayerTurn(), selectedTile, destinationTile);
        updateBoard();
        selectedTile = null;
        destinationTile = null;
    }

    private void updateBoard() {
        for (int i = 0; i < model.getBoard().getLength(); i++) {
            for (int j = 0; j < model.getBoard().getWidth(); j++) {
                if (model.getTile(i, j).getPiece() != null) {
                    view.getCenterPanel().getTileGUI(i, j).setImage(model.getTile(i, j).getPiece().toString() + ".png");
                } else {
                    view.getCenterPanel().getTileGUI(i, j).setImage(null);
                }
            }
        }
    }

}
