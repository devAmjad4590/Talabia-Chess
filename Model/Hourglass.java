package Model;

/**
 * The Hourglass class represents an hourglass-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Hourglass pieces have a color (yellow or not yellow).
 * Class implemented by Amgad Elrashid Gurashi Eltayeb
 */

public class Hourglass extends Piece {

    /**
     * Constructs a new Hourglass piece with the specified color.
     *
     * @param yellow true if the hourglass is yellow, false is blue.
     */
    public Hourglass(boolean yellow) {
        super(yellow);
    }

    /**
     * Determines if the Hourglass can move from the current tile to the new tile.
     * The Hourglass can move diagonally by two squares.
     *
     * @param currentTile The current tile of the Hourglass.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        // Calculating the number of tiles travelled by
        // using the absolute value of the current and destination tile.
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());

        // Checks if the piece is making the correct L movement
        if (xTiles * yTiles == 2) {
            return true;
        }

        // need another if condition to see if the piece movement will result in an
        // exposed check

        return false;

    }

}
