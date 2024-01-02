package Model;

/**
 * The `PlusMovement` class represents a concrete implementation of the `PieceMovement` interface.
 * It defines the specific plus-shaped movement behavior for a game piece, following the strategy pattern.
 * class implemented by Amgad Elrashid Gurashi Eltayeb
 */
public class PlusMovement implements PieceMovement {

    /**
     * Checks if the specified movement is valid for plus-shaped movement.
     *
     * @param xTiles The number of tiles to move horizontally.
     * @param yTiles The number of tiles to move vertically.
     * @return True if the movement is valid for plus-shaped movement, false otherwise.
     */
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Checks if the piece is traveling through the rows or columns
        if ((xTiles > 0 && yTiles == 0) || (yTiles > 0 && xTiles == 0)) {
            return true;
        }

        return false;
    }
}
