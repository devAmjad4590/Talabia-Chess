package Model;

import Model.Pieces.Piece;
import Model.Pieces.Sun;

/**
 * The `Move` class encapsulates the logic for moving pieces on the game board.
 * It provides methods for validating moves, executing moves
 * and setting the current player associated with the move.
 * This class is implemented by Amgad Elrashid Gurashi Eltayeb.
 */
public class Move {
    private Player currentPlayer; // The current player making the move

    /**
     * Constructs a new instance of the `Move` class with the specified current
     * player.
     * 
     * @param currentPlayer The player making the move.
     */
    public Move(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the player's move from the current tile to the new tile on the game
     * board.
     * Updates the piece positions and maintains the board state.
     * 
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     */
    public void setPlayerMove(Tile currentTile, Tile newTile) {
        Piece piece = currentTile.getPiece();
        isEnemy(newTile);
        newTile.setPiece(piece);
        currentTile.setPiece(null);
        Board.getMap().put(piece, newTile);
    }

    /**
     * Checks if a move from the current tile to the new tile is valid based on game
     * rules.
     * Validates the move in terms of piece movement, player ownership, and tile
     * conditions.
     * 
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece is intended to be
     *                    moved.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isMoveValid(Tile currentTile, Tile newTile) {
        Piece playerPiece = currentTile.getPiece();

        if (playerPiece == null || !playerPiece.canMove(currentTile, newTile)
                || currentPlayer.isYellow() != playerPiece.isYellow()
                || currentTile == newTile) {
            return false;
        }

        if (newTile.getPiece() != null && newTile.getPiece().isYellow() == playerPiece.isYellow()) {
            return false;
        }

        return true;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Moves a piece from the current tile to the new tile.
     * If the new tile is occupied by an opponent's piece, the opponent's piece is
     * captured.
     *
     * @param currentTile The current tile of the piece.
     * @param newTile     The new tile where the piece will be moved.
     */
    private void isEnemy(Tile newTile) {
        Piece capturedPiece = newTile.getPiece();
        if (capturedPiece != null) {
            Board.getMap().remove(capturedPiece); // needs testing
            isSunCaptured(capturedPiece);
        }
    }

    public boolean isSunCaptured(Piece piece) {
        if (piece instanceof Sun) {
            return true;
        }
        return false;
    }
}
