package Model.Pieces;

import Model.Board;
import Model.Tile;
import Model.Movements.BackwardMovement;
import Model.Movements.ForwardMovement;

/**
 * The Point class represents a point-shaped chess piece.
 * It extends the Piece class and implements specific rules for movement.
 * Point pieces have a color (yellow or not yellow).
 * This class is part of the template method design pattern.
 * Class implemented by Amgad Elrashid Gurashi Eltayeb and Maher M. N. Balchi
 */
public class Point extends Piece {

    /**
     * Constructs a new Point piece with the specified color.
     *
     * @param yellow true if the point is yellow, false if blue.
     * Constructor implemented by Amgad Elrashid Gurashi Eltayeb 
     */
    public Point(boolean yellow) {
        super(yellow);
        setPieceMovement(new ForwardMovement());
    }

    /**
     * Determines if the Point can move from the current tile to the new tile.
     * The Point can move forward one or two squares.
     *
     * @param currentTile The current tile of the Point.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     * Method implemented by Amgad Elrashid Gurashi Eltayeb and Maher M. N. Balchi
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int xTiles = currentTile.getX() - newTile.getX();
        int yTiles = currentTile.getY() - newTile.getY();
        if (!getPieceMovement().isValid(xTiles, yTiles)) {
            return false;
        }

        if (canPass(currentTile, newTile, xTiles, yTiles)) {
            return true;
        }
        return false;
    }

    /**
     * Sets the movement of the piece to forward.
     * Method implemented by Maher M. N. Balchi
     */
    public void setMovementForward() {
        setPieceMovement(new ForwardMovement());
    }

    /**
     * Sets the movement of the piece to backward.
     * Method implemented by Maher M. N. Balchi
     */
    public void setMovementBackward() {
        setPieceMovement(new BackwardMovement());
    }

    /**
     * Switches the movement of the piece between forward and backward.
     * Method implemented by Maher M. N. Balchi
     */
    public void switchMovement() {
        if (getPieceMovement() instanceof ForwardMovement) {
            setMovementBackward();
        } else {
            setMovementForward();
        }
    }

    /**
     * Checks if the piece can pass through the tiles between the current and new tiles.
     *
     * @param currentTile The current tile of the Point.
     * @param newTile     The destination tile.
     * @param xTiles      The difference in x-coordinates between current and new tiles.
     * @param yTiles      The difference in y-coordinates between current and new tiles.
     * @return true if the piece can pass, false otherwise.
     * Method implemented by Maher M. N. Balchi
     */
    @Override
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        for (int i = 1; i < Math.abs(xTiles); i++) {
            if (Board.getTile(Math.abs(currentTile.getX() - i), currentTile.getY()).getPiece() != null) {
                return false;
            }
        }
        return true;
    }
  
    // Additional methods could be placed here if necessary
}
