package Model.Movements;

/**
 * The `ForwardMovement` class represents a concrete implementation of the `PieceMovement` interface.
 * It defines the specific forward movement behavior for a game piece, following the strategy pattern.
 * class implemented by mamoon
 */
public class ForwardMovement implements PieceMovement {

    /**
     * Checks if the specified movement is valid for forward movement.
     *
     * @param xTiles The number of tiles to move vertically.
     * @param yTiles The number of tiles to move horizontally.
     * @return True if the movement is valid for forward movement, false otherwise.
     */
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Valid forward movement conditions: Move right by 1 or 2 tiles, and no vertical movement.
        if ((xTiles == 1 || xTiles == 2) && yTiles == 0) {
            return true;
        }
        return false;
    }
}
