package Model;

/**
 * The Point class represents a point-shaped chess piece.
 * It extends the Piece class and implements specific rules for movement.
 * Point pieces have a color (yellow or not yellow).
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
        if (yellow) {
            setPieceMovement(new ForwardMovement());
        } else {
            setPieceMovement(new BackwardMovement());
        }
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
        return getPieceMovement().canMove(currentTile, newTile);
        
    }

    public void setMovementForward(){
        setPieceMovement(new ForwardMovement());
    }

    public void setMovementBackward(){
        setPieceMovement(new BackwardMovement());
    }


}
