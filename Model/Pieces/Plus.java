package Model.Pieces;

import java.util.HashSet;

import Model.Board;
import Model.Tile;
import Model.Movements.PlusMovement;

/**
 * The Plus class represents a plus-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
 * This class is part of the template method design pattern.
 * Class implemented by Amgad ELrashid Gurashi Eltayeb and Maher M. N. Balchi
 */
public class Plus extends Piece {

    /**
     * Constructs a new Plus piece with the specified color.
     *
     * @param yellow true if the plus is yellow, false if blue.
     */
    public Plus(boolean yellow) {
        // Call the constructor of the superclass (Piece)
        super(yellow);
        // Set the piece movement behavior using PlusMovement
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
        // Calculate the difference in coordinates
        int yTiles = currentTile.getY() - newTile.getY();
        int xTiles = currentTile.getX() - newTile.getX();

        // Check if the move is valid based on PlusMovement rules
        if (!getPieceMovement().isValid(xTiles, yTiles)) {
            return false;
        }

        // Check if the Plus can pass to the new tile
        if (canPass(currentTile, newTile, xTiles, yTiles)) {
            return true;
        }

        // If none of the conditions are met, return false
        return false;
    }

    /**
     * Checks if the Plus can pass from the current tile to the new tile.
     * This method is used in conjunction with calculateMoves to check the path.
     *
     * @param currentTile The current tile of the Plus.
     * @param newTile     The destination tile.
     * @param xTiles      The horizontal difference in tiles.
     * @param yTiles      The vertical difference in tiles.
     * @return true if the Plus can pass, false otherwise.
     */
    @Override
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        // Calculate the available moves based on the path
        HashSet<Tile> availableMoves = calculateMoves(currentTile, newTile, xTiles, yTiles);

        // Check if the new tile is in the set of available moves
        if (availableMoves.contains(newTile)) {
            return true;
        }
        // If not, return false
        return false;
    }

    // Helper methods for checking specific directions
    private boolean isMoveLeft(int xTiles, int yTiles) {
        return xTiles == 0 && yTiles > 0;
    }

    private boolean isMoveRight(int xTiles, int yTiles) {
        return xTiles == 0 && yTiles < 0;
    }

    private boolean isMoveUp(int xTiles, int yTiles) {
        return xTiles > 0 && yTiles == 0;
    }

    private boolean isMoveDown(int xTiles, int yTiles) {
        return xTiles < 0 && yTiles == 0;
    }

    // Helper methods for checking if the specific path is empty using getters
    private boolean checkLeftPath(Tile currentTile, int i) {
        return getLeftTile(currentTile, i).getPiece() == null;
    }

    private boolean checkRightPath(Tile currentTile, int i) {
        return getRightTile(currentTile, i).getPiece() == null;
    }

    private boolean checkUpPath(Tile currentTile, int i) {
        return getUpTile(currentTile, i).getPiece() == null;
    }

    private boolean checkDownPath(Tile currentTile, int i) {
        return getDownTile(currentTile, i).getPiece() == null;
    }

    // Helper methods for retrieving tiles in different directions starting from the current tile using an iterator
    private Tile getUpTile(Tile currentTile, int i) {
        return Board.getTile(Math.abs(currentTile.getX() - i), currentTile.getY());
    }

    private Tile getDownTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX() + i, currentTile.getY());
    }

    private Tile getLeftTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX(), Math.abs(currentTile.getY() - i));
    }

    private Tile getRightTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX(), currentTile.getY() + i);
    }

    /**
     * Calculates and returns a set of tiles representing valid moves for the Plus piece.
     * The set includes tiles that the Plus can move through to reach the destination.
     *
     * @param currentTile The current tile of the Plus.
     * @param newTile     The destination tile.
     * @param xTiles      The horizontal difference in tiles.
     * @param yTiles      The vertical difference in tiles.
     * @return A HashSet of tiles representing valid moves.
     */
    public HashSet<Tile> calculateMoves(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        HashSet<Tile> tiles = new HashSet<Tile>();

        // Check and add tiles for moving right
        if (isMoveRight(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(yTiles); i++) {
                tiles.add(getRightTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkRightPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Check and add tiles for moving left
        if (isMoveLeft(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(yTiles); i++) {
                tiles.add(getLeftTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkLeftPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Check and add tiles for moving up
        if (isMoveUp(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getUpTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkUpPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Check and add tiles for moving down
        if (isMoveDown(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getDownTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkDownPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Return the set of valid moves
        return tiles;
    }
}
