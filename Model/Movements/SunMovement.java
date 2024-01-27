package Model.Movements;

/**
 * The `SunMovement` class represents a concrete implementation of the `PieceMovement` interface.
 * It defines the specific sun-shaped movement behavior for a game piece, following the strategy pattern.
 * class implemented by Mamoon T. M. Almazloom
 */
public class SunMovement implements PieceMovement {

    /**
     * Checks if the specified movement is valid for sun-shaped movement.
     *
     * @param xTiles The number of tiles to move horizontally.
     * @param yTiles The number of tiles to move vertically.
     * @return True if the movement is valid for sun-shaped movement, false otherwise.
     */
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Checks if the piece is moving diagonally or horizontally/vertically by one tile
        if ((xTiles == 1 && yTiles == 1) || (xTiles == 1 && yTiles == 0) || (xTiles == 0 && yTiles == 1)) {
            return true;
        }

        return false;
    }
}
