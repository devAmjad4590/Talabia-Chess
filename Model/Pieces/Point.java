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
 * Class implemented by Amgad Elrashid Gurashi Eltayeb
 */
public class Point extends Piece {
    /**
     * Constructs a new Point piece with the specified color.
     *
     * @param yellow true if the point is yellow, false is blue.
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
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int xTiles = currentTile.getX() - newTile.getX(); // 2
        int yTiles = currentTile.getY() - newTile.getY(); //
        if (!getPieceMovement().isValid(xTiles, yTiles)) {
            return false;
        }

        if (canPass(currentTile, newTile, xTiles, yTiles)) {
            return true;
        }
        return false;

    }

    private void setMovementForward() {
        setPieceMovement(new ForwardMovement());
    }

    private void setMovementBackward() {
        setPieceMovement(new BackwardMovement());
    }

    public void switchMovement() {
        if (getPieceMovement() instanceof ForwardMovement) {
            setMovementBackward();
        } else
            setMovementForward();

    }

    @Override
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
            for (int i = 1; i < Math.abs(xTiles); i++) {
                if (Board.getTile(Math.abs(currentTile.getX() - i), currentTile.getY()).getPiece() != null) {
                    return false;
                }
            }
            return true;
} 
    
    
        // other methods
    }
