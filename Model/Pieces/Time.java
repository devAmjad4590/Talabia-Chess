package Model.Pieces;

import java.util.*;
import Model.Board;
import Model.Tile;
import Model.Movements.TimeMovement;

/**
 * The Time class represents a x-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
 * This class is part of the template method design pattern.
 * Class implemented by Amgad ELrashid Gurashi Eltayeb
 */

public class Time extends Piece {

    /**
     * Constructs a new Time piece with the specified color.
     *
     * @param yellow true if the plus is yellow, false is blue.
     */
    public Time(boolean yellow) {
        super(yellow);
        setPieceMovement(new TimeMovement());
    }

    /**
     * Determines if the Time can move from the current tile to the new tile.
     * The Time can move horizontally or vertically any number of squares.
     *
     * @param currentTile The current tile of the Time.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int yTiles = currentTile.getY() - newTile.getY();
        int xTiles = currentTile.getX() - newTile.getX();

        if (!getPieceMovement().isValid(xTiles, yTiles)) {
            return false;
        }
        if (canPass(currentTile, newTile, xTiles, yTiles)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) { // needs testing specifically
                                                                                     // rightDown and
        // UpLeft movements
        HashSet<Tile> availableMoves = calculateMoves(currentTile, newTile, xTiles, yTiles);
        if (availableMoves.contains(newTile) && !currentTile.equals(newTile)) {
            return true;
        }
        return false;
    }

    private boolean isMoveUpRight(int xTiles, int yTiles) {
        return xTiles - yTiles > 0;
    }

    private boolean isMoveDownLeft(int xTiles, int yTiles) {
        return xTiles - yTiles < 0;
    }

    private boolean isMoveDownRight(int xTiles, int yTiles) {
        return xTiles - yTiles == 0 && yTiles < 0;
    }

    private boolean isMoveUpLeft(int xTiles, int yTiles) {
        return xTiles - yTiles == 0 && yTiles > 0;
    }

    private boolean checkUpRightPath(Tile currentTile, int i) {
        return getUpRightTile(currentTile, i).getPiece() == null;
    }

    private Tile getUpRightTile(Tile currentTile, int i) {
        return Board.getTile(Math.abs(currentTile.getX() - i), Math.abs(currentTile.getY() + i));
    }

    private boolean checkDownLeftPath(Tile currentTile, int i) {
        return getDownLeftTile(currentTile, i).getPiece() == null;
    }

    private Tile getDownLeftTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX() + i, currentTile.getY() - i);
    }

    private boolean checkUpLeftPath(Tile currentTile, int i) {
        return getUpLeftTile(currentTile, i).getPiece() == null;
    }

    private Tile getUpLeftTile(Tile currentTile, int i) {
        return Board.getTile(Math.abs(currentTile.getX() - i), Math.abs(currentTile.getY() - i));
    }

    private boolean checkDownRightPath(Tile currentTile, int i) {
        return getDownRightTile(currentTile, i).getPiece() == null;
    }

    private Tile getDownRightTile(Tile currentTile, int i) {
        return Board.getTile((currentTile.getX() + i), currentTile.getY() + i);
    }

    public HashSet<Tile> calculateMoves(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        HashSet<Tile> tiles = new HashSet<Tile>();
        if (isMoveUpRight(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getUpRightTile(currentTile, i));
                if (!checkUpRightPath(currentTile, i)) {
                    break;
                }

            }
        }
        if (isMoveDownLeft(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                System.out.println("down left");
                tiles.add(getDownLeftTile(currentTile, i));
                if (!checkDownLeftPath(currentTile, i)) {
                    break;
                }

            }

        }
        if (isMoveUpLeft(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getUpLeftTile(currentTile, i));
                if (!checkUpLeftPath(currentTile, i)) {
                    break;
                }

            }
        }

        if (isMoveDownRight(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getDownRightTile(currentTile, i));
                if (!checkDownRightPath(currentTile, i)) {
                    break;
                }

            }
        }

        return tiles;
    }

}
