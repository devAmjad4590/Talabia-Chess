package Model;

/**
 * The `BackwardMovement` class represents a concrete implementation of the `PieceMovement` interface.
 * It defines the specific backward movement behavior for a game piece, following the strategy pattern.
 * class implemented by mamoon
 */
public class BackwardMovement implements PieceMovement {

    /**
     * Checks if the specified movement is valid for backward movement.
     *
     * @param xTiles The number of tiles to move horizontally.
     * @param yTiles The number of tiles to move vertically.
     * @return True if the movement is valid for backward movement, false otherwise.
     */
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Valid backward movement conditions: Move left by 1 or 2 tiles, and no vertical movement.
        if ((xTiles == -1 || xTiles == -2) && yTiles == 0) {
            return true;
        }

        return false;
    }
}
