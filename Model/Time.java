package Model;

/**
 * The Time class represents a x-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
 * Class implemented by Amgad ELrashid Gurashi Eltayeb
 */

public class Time extends Piece{

    /**
     * Constructs a new Time piece with the specified color.
     *
     * @param yellow true if the plus is yellow, false is blue.
     */
    public Time(boolean yellow) {
        super(yellow);
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
        // Calculating the number of tiles travelled by 
        //using the absolute value of the current and destination tile.
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());

        // Checks if the pieces is moving diagonally.
        if(xTiles == yTiles && newTile.getPiece() == null){
            return true;
        }

        return false;

        // need another if condition to see if the piece movement will result in an exposed check

    }
    
    public String toString(){
        return "Time";
    }
}
