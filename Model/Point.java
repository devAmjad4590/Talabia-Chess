package Model;

/**
 * The Point class represents a point-shaped chess piece.
 * It extends the Piece class and implements specific rules for movement.
 * Point pieces have a color (yellow or not yellow).
 * Class implemented by Amgad Elrashid Gurashi Eltayeb
 */
public class Point extends Piece {

    private boolean isFacingUp;

    /**
     * Constructs a new Point piece with the specified color.
     *
     * @param yellow true if the point is yellow, false is blue.
     */
    public Point(boolean yellow) {
        super(yellow);
        checkDirection();
    }

    /**
     * Determines if the Point can move from the current tile to the new tile.
     * The Point can move forward one or two squares.
     *
     * @param currentTile The current tile of the Point.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        // Calculating the number of tiles travelled by
        // using the absolute value of the current and destination tile.
        int xTiles = currentTile.getX() - newTile.getX();

        // Check if the Plus piece is moving one or two tiles forward
        if (xTiles == 1 || xTiles == 2 && isFacingUp) {
            return true;
        }

        if(xTiles == -1 || xTiles == -2 && !isFacingUp){
            return true;
        }
        // need to implement a way to make sure the piece is only moving the direction
        // its pointing.

        // need another if condition to see if the piece movement will result in an
        // exposed check

        return false;
    }

    private void checkDirection() {
        if (this.isYellow()) {
            isFacingUp = true;
        } else {
            isFacingUp = false;
        }
    }

    public void changeDirection() {
        if (isFacingUp) {
            isFacingUp = false;
        } else {
            isFacingUp = true;
        }
    }


}
