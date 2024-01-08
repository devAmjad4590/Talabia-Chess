package Model.Pieces;

import Model.Board;
import Model.Tile;
import Model.Movements.PlusMovement;

/**
 * The Plus class represents a plus-shaped piece.
 * It extends the Piece class and implements specific rules for movement.
 * Plus pieces have a color (yellow or not yellow).
 * Class implemented by Amgad ELrashid Gurashi Eltayeb
 */
public class Plus extends Piece {

    /**
     * Constructs a new Plus piece with the specified color.
     *
     * @param yellow true if the plus is yellow, false is blue.
     */
    public Plus(boolean yellow) {
        super(yellow);
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
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());

        if (!getPieceMovement().isValid(xTiles, yTiles)) {
            return false;
        }

        if(canPass(currentTile, newTile, xTiles, yTiles))
        {
            return true;
        }

        return false;
    
        
    }

    @Override
    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        Board board = Board.getInstance();
        if (xTiles > 0 && yTiles == 0) {
            for (int i = 1; i < Math.abs(xTiles); i++) {
                if (!(board.getTile(Math.abs(currentTile.getX() - i), currentTile.getY()).getPiece() == null)) {
                    return false;
                }
            }
            return true;
        } else if (xTiles == 0 && yTiles > 0) {
            for (int i = 1; i < Math.abs(yTiles); i++) {
                if (!(board.getTile(currentTile.getX(), Math.abs(currentTile.getY() - i)).getPiece() == null)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}