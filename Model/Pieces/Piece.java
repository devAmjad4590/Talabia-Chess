package Model.Pieces;

import Model.Tile;
import Model.Movements.HourglassMovement;
import Model.Movements.PieceMovement;

/**
 * The {@code Piece} class is an abstract base class representing a chess piece
 * in a chess game.
 * It includes information about whether the piece has been captured and its
 * color.
 * Subclasses must implement the {@code canMove} method to determine the piece's
 * valid moves.
 * This class is part of the template method design pattern.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public abstract class Piece {
    private boolean yellow;
    private PieceMovement pieceMovement;

    /**
     * Constructs a new Piece instance with the specified color.
     *
     * @param yellow Indicates the color of the piece. True for yellow, false for
     *               another color.
     */
    public Piece(boolean yellow) {
        this.yellow = yellow;
    }

    /**
     * Gets the color of the piece.
     *
     * @return {@code true} if the piece is yellow, {@code false} otherwise.
     */
    public boolean isYellow() {
        return yellow;
    }

    // method added by Amgad Elrashid Gurashi Eltayeb
    public boolean sameColor(Piece otherPiece) {
        return this.yellow == otherPiece.yellow;
    }

    /**
     * Abstract method that must be implemented by subclasses to determine the valid
     * moves of the piece.
     *
     * @param currentTile The current tile position of the piece.
     * @param newTile     The new tile position to check for a valid move.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    public abstract boolean canMove(Tile currentTile, Tile newTile);

    public void setPieceMovement(PieceMovement pieceMovement) {
        this.pieceMovement = pieceMovement;
    }

    public PieceMovement getPieceMovement() {
        return pieceMovement;
    }

    public String toString() {
        // return the piece type
        return getColor() + this.getClass().getSimpleName();
    }

    private String getColor() {
        return yellow ? "yellow" : "blue";
    }

    public boolean canPass(Tile currentTile, Tile newTile, int xTiles, int yTiles) {
        return true;
    }
}
