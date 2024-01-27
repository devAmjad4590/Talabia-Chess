package Model.Pieces;

import java.util.HashSet;
import Model.Board;
import Model.Tile;
import Model.Movements.TimeMovement;

/**
 * The Time class represents an X-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Time pieces have a color (yellow or not yellow).
 * This class is part of the template method design pattern.
 * Class implemented by Amgad ELrashid Gurashi Eltayeb and Maher M. N. Balchi
 */
public class Time extends Piece {

    /**
     * Constructs a new Time piece with the specified color.
     *
     * @param yellow true if the time is yellow, false if blue.
     */
    public Time(boolean yellow) {
        super(yellow);
        setPieceMovement(new TimeMovement());
    }

    /**
     * Determines if the Time can move from the current tile to the new tile.
     * The Time can move diagonally in any direction.
     *
     * @param currentTile The current tile of the Time.
     * @param newTile     The destination tile.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int yTiles = currentTile.getY() - newTile.getY();
        int xTiles = currentTile.getX() - newTile.getX();

        // Check if the move is valid based on TimeMovement rules
        if (!getPieceMovement().isValid(xTiles, yTiles)) {
            return false;
        }

        // Check if the Time can pass to the new tile
        if (canPass(currentTile, newTile, xTiles, yTiles)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the Time can pass from the current tile to the new tile.
     * This method is used in conjunction with calculateMoves to check the path.
     *
     * @param currentTile The current tile of the Time.
     * @param newTile     The destination tile.
     * @param xTiles      The horizontal difference in tiles.
     * @param yTiles      The vertical difference in tiles.
     * @return true if the Time can pass, false otherwise.
     */
    @Override
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        // Calculate the available moves based on the path
        HashSet<Tile> availableMoves = calculateMoves(currentTile, newTile, xTiles, yTiles);

        // Check if the new tile is in the set of available moves and not the same as the current tile
        if (availableMoves.contains(newTile) && !currentTile.equals(newTile)) {
            return true;
        }
        // If not, return false
        return false;
    }

    // Helper methods for checking specific directions
    private boolean isMoveUpRight(int xTiles, int yTiles) {
        return xTiles - yTiles > 0;
    }

    private boolean isMoveDownLeft(int xTiles, int yTiles) {
        return xTiles - yTiles < 0;
    }

    private boolean isMoveDownRight(int xTiles, int yTiles) {
        return xTiles - yTiles == 0 && yTiles < 0;
    }

    private boolean isMoveUpLeft(int xTiles, int yTiles) {
        return xTiles - yTiles == 0 && yTiles > 0;
    }

    // Helper methods for checking if the specific path is empty using getters
    private boolean checkUpRightPath(Tile currentTile, int i) {
        return getUpRightTile(currentTile, i).getPiece() == null;
    }

    private boolean checkDownLeftPath(Tile currentTile, int i) {
        return getDownLeftTile(currentTile, i).getPiece() == null;
    }

    private boolean checkUpLeftPath(Tile currentTile, int i) {
        return getUpLeftTile(currentTile, i).getPiece() == null;
    }

    private boolean checkDownRightPath(Tile currentTile, int i) {
        return getDownRightTile(currentTile, i).getPiece() == null;
    }

    // Helper methods for retrieving tiles in different directions starting from the current tile using an iterator
    private Tile getUpRightTile(Tile currentTile, int i) {
        return Board.getTile(Math.abs(currentTile.getX() - i), Math.abs(currentTile.getY() + i));
    }

    private Tile getDownLeftTile(Tile currentTile, int i) {
        return Board.getTile(currentTile.getX() + i, currentTile.getY() - i);
    }

    private Tile getUpLeftTile(Tile currentTile, int i) {
        return Board.getTile(Math.abs(currentTile.getX() - i), Math.abs(currentTile.getY() - i));
    }

    private Tile getDownRightTile(Tile currentTile, int i) {
        return Board.getTile((currentTile.getX() + i), currentTile.getY() + i);
    }

    /**
     * Calculates and returns a set of tiles representing valid moves for the Time piece.
     * The set includes tiles that the Time can move through to reach the destination.
     *
     * @param currentTile The current tile of the Time.
     * @param newTile     The destination tile.
     * @param xTiles      The horizontal difference in tiles.
     * @param yTiles      The vertical difference in tiles.
     * @return A HashSet of tiles representing valid moves.
     */
    public HashSet<Tile> calculateMoves(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        HashSet<Tile> tiles = new HashSet<Tile>();

        // Check and add tiles for moving diagonally up-right
        if (isMoveUpRight(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getUpRightTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkUpRightPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Check and add tiles for moving diagonally down-left
        if (isMoveDownLeft(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                System.out.println("down left");
                tiles.add(getDownLeftTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkDownLeftPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Check and add tiles for moving diagonally up-left
        if (isMoveUpLeft(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getUpLeftTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkUpLeftPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Check and add tiles for moving diagonally down-right
        if (isMoveDownRight(xTiles, yTiles)) {
            for (int i = 1; i <= Math.abs(xTiles); i++) {
                tiles.add(getDownRightTile(currentTile, i));
                // Break the loop if there is an obstacle in the path
                if (!checkDownRightPath(currentTile, i)) {
                    break;
                }
            }
        }

        // Return the set of valid moves
        return tiles;
    }
}

