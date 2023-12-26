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
        setPieceMovement(new SunMovement());
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
        return getPieceMovement().canMove(currentTile, newTile); 
    }
    
    
}
