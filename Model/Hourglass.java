package Model;

/**
 * The Hourglass class represents an hourglass-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Hourglass pieces have a color (yellow or not yellow).
 * Class implemented by Amgad Elrashid Gurashi Eltayeb
 */

public class Hourglass extends Piece {

    /**
     * Constructs a new Hourglass piece with the specified color.
     *
     * @param yellow true if the hourglass is yellow, false is blue.
     */
    public Hourglass(boolean yellow) {
        super(yellow);
        setPieceMovement(new HourglassMovement());
    }

    /**
     * Determines if the Hourglass can move from the current tile to the new tile.
     * The Hourglass can move diagonally by two squares.
     *
     * @param currentTile The current tile of the Hourglass.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());
        
        if(getPieceMovement().isValid(xTiles, yTiles)){
            return true;
        }
        return false;
    }

}
