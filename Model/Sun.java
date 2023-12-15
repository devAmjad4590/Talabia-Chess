package Model;

/**
 * The Sun class represents a sun-shaped chess piece.
 * It extends the Piece class and implements specific rules for movement.
 * Sun pieces have a color (yellow or not yellow).
 * Class implemented by Amgad ELrashid Gurashi Eltayeb
 */
public class Sun extends Piece{

     /**
     * Constructs a new Sun piece with the specified color.
     *
     * @param yellow true if the sun is yellow, false otherwise.
     */
    public Sun(boolean yellow) {
        super(yellow);
    }

     /**
     * Determines if the Sun can move from the current tile to the new tile.
     * The Sun can move to any adjacent square, horizontally, vertically, or diagonally.
     *
     * @param currentTile The current tile of the Sun.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        // Calculating the number of tiles travelled by 
        //using the absolute value of the current and destination tile.
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());

        // Check if the destination tile has the same color piece
        if(this.sameColor(newTile.getPiece())){
            return false;
        }

        //Check if the piece is moving to the adjacent tiles or diagnocally once
        if(xTiles + yTiles == 1 || (xTiles == 1 && yTiles == 1)){
            return true;
        }

        

        // need another if condition to see if the piece movement will result in an exposed check

        return false;
        
    }
    
}
