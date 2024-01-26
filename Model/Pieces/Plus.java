package Model.Pieces;

import java.util.HashSet;

import Model.Board;
import Model.Tile;
import Model.Movements.PlusMovement;

/**
 * The Plus class represents a plus-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
 * This class is part of the template method design pattern.
 * Class implemented by Amgad ELrashid Gurashi Eltayeb
 */
public class Plus extends Piece {

    /**
     * Constructs a new Plus piece with the specified color.
     *
     * @param yellow true if the plus is yellow, false is blue.
     */
    public Plus(boolean yellow) {
        super(yellow);
        setPieceMovement(new PlusMovement());
    }

    /**
     * Determines if the Plus can move from the current tile to the new tile.
     * The Plus can move horizontally or vertically any number of squares.
     *
     * @param currentTile The current tile of the Plus.
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
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) { // Last right and down tile wont
                                                                                     // work
        HashSet<Tile> availableMoves = calculateMoves(currentTile, newTile, xTiles, yTiles);

        if (availableMoves.contains(newTile)) {
            return true;
        }
        return false;
    }

    private boolean isMoveLeft(int xTiles, int yTiles) {
        return xTiles == 0 && yTiles > 0;
    }

    private boolean isMoveRight(int xTiles, int yTiles) { //

        return xTiles == 0 && yTiles < 0;
    }

    private boolean isMoveUp(int xTiles, int yTiles) {
        return xTiles > 0 && yTiles == 0;
    }

    private boolean isMoveDown(int xTiles, int yTiles) { //
        return xTiles < 0 && yTiles == 0;
    }

    private boolean checkLeftPath(Tile currentTile, int i) {
        return getLeftTile(currentTile, i).getPiece() == null;
    }

    private boolean checkRightPath(Tile currentTile, int i) {
        return getRightTile(currentTile, i).getPiece() == null;
    }

    private boolean checkUpPath(Tile currentTile, int i) {
        return getUpTile(currentTile, i).getPiece() == null;
    }

    private boolean checkDownPath(Tile currentTile, int i) {
        return getDownTile(currentTile, i).getPiece() == null;
    }

    private Tile getUpTile(Tile currentTile, int i) {
        return Board.getTile(Math.abs(currentTile.getX() - i), currentTile.getY());
    }

    private Tile getDownTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX() + i, currentTile.getY()); /// returns false why?
    }

    private Tile getLeftTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX(), Math.abs(currentTile.getY() - i));
    }

    private Tile getRightTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX(), currentTile.getY() + i);
    }

    public HashSet<Tile> calculateMoves(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        HashSet<Tile> tiles = new HashSet<Tile>();
        if (isMoveRight(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(yTiles); i++) {
                tiles.add(getRightTile(currentTile, i));
                if (!checkRightPath(currentTile, i)) {
                    break;
                }
            }
        }

        if (isMoveLeft(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(yTiles); i++) {
                tiles.add(getLeftTile(currentTile, i));
                if (!checkLeftPath(currentTile, i)) {
                    break;
                }
            }
        }

        if (isMoveUp(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getUpTile(currentTile, i));
                if (!checkUpPath(currentTile, i)) {
                    break;
                }
            }
        }

        if (isMoveDown(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getDownTile(currentTile, i));
                if (!checkDownPath(currentTile, i)) {
                    break;
                }
            }

        }
        return tiles;

    }
}