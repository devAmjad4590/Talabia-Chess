package Model;

/**
 * The `TimeMovement` class represents a concrete implementation of the `PieceMovement` interface.
 * It defines the specific time-shaped movement behavior for a game piece, following the strategy pattern.
 * class implemented by mamoon
 */
public class TimeMovement implements PieceMovement {

    /**
     * Checks if the specified movement is valid for time-shaped movement.
     *
     * @param xTiles The number of tiles to move horizontally.
     * @param yTiles The number of tiles to move vertically.
     * @return True if the movement is valid for time-shaped movement, false otherwise.
     */
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Checks if the piece is moving diagonally
        if (xTiles == yTiles) {
            return true;
        }

        return false;
    }
}
