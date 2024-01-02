package Model;

/**
 * The `HourglassMovement` class represents a concrete implementation of the `PieceMovement` interface.
 * It defines the specific hourglass-shaped movement behavior for a game piece, following the strategy pattern.
 * class implemented by Amgad Elrashid Gurashi Eltayeb
 */
public class HourglassMovement implements PieceMovement {

    /**
     * Checks if the specified movement is valid for hourglass-shaped movement.
     *
     * @param xTiles The number of tiles to move horizontally.
     * @param yTiles The number of tiles to move vertically.
     * @return True if the movement is valid for hourglass-shaped movement, false otherwise.
     */
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Valid hourglass-shaped movement conditions: Move either 1 tile horizontally and 2 tiles vertically
        // or 2 tiles horizontally and 1 tile vertically.
        if ((xTiles == 1 && yTiles == 2) || (xTiles == 2 && yTiles == 1)) {
            return true;
        }
        return false;
    }
}
