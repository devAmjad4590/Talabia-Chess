package Model;

/**
 * The Plus class represents a plus-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
 * Class implemented by Amgad ELrashid Gurashi Eltayeb
 */
public class Plus extends Piece{

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
       return getPieceMovement().canMove(currentTile, newTile); 
    }

      

}

