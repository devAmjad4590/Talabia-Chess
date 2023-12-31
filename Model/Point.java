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
        int xTiles = currentTile.getX() - newTile.getX();
        int yTiles = currentTile.getY() - newTile.getY();

        if(getPieceMovement().isValid(xTiles, yTiles)){
            return true;
        }
        return false;
        
    }

    public void setMovementForward(){
        setPieceMovement(new ForwardMovement());
    }

    public void setMovementBackward(){
        setPieceMovement(new BackwardMovement());
    }

    @Override
    public boolean canPass(Tile currentTile, int movement){ 
        if (Board.getInstance().getTile(currentTile.getX() - movement, currentTile.getY()) == null && currentTile.getX() - movement > 0 ) {
            return true;
        }
        return false;
        
    }
}
