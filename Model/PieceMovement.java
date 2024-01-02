package Model;

/**
 * The `PieceMovement` interface defines the contract for movement behaviors of game pieces.
 * It is implemented by the concrete movement classes, following the strategy pattern.
 * class implemented by Amgad Elrashid Gurashi Eltayeb
 */
public interface PieceMovement {

    /**
     * Checks if the specified movement is valid based on the implementing class's rules.
     *
     * @param xTiles The number of tiles to move vertically.
     * @param yTiles The number of tiles to move horizontally.
     * @return True if the movement is valid, false otherwise.
     */
    boolean isValid(int xTiles, int yTiles);
}
