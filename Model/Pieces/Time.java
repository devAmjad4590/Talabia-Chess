package Model.Pieces;

import Model.Board;
import Model.Tile;
import Model.Movements.TimeMovement;

/**
 * The Time class represents a x-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
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
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        Board board = Board.getInstance();

        for (int i = 1; i < Math.abs(xTiles); i++) {
            if (xTiles * yTiles > 0) {
                if (!(board.getTile(Math.abs(currentTile.getX() - i), Math.abs(currentTile.getY() - i))
                        .getPiece() == null)) {
                    return false;
                }
            } else if (xTiles * yTiles < 0) {
                if (!(board.getTile(Math.abs(currentTile.getX() - i), Math.abs(currentTile.getY() + i))
                        .getPiece() == null)) {
                    return false;
                }

            }

        }

        return true;
    }

}
