package Model;

/**
 * The `Game` class represents the main logic and state of a game.
 * It is implemented by Amgad Elrashid Gurashi Eltayeb.
 */
public class Game  {
    private Board board; // The game board
    private PlayerManager playerManager; // The player manager
    private Player currentPlayer; // The current player

    /**
     * Initializes the game by creating a player manager instance and a game board.
     */
    public Game() {
        init();
    }

    private void init() {
        board = Board.getInstance();
        playerManager = new PlayerManager();
        currentPlayer = playerManager.getCurrentPlayer();
    }

    /**
     * Gets the game board.
     *
     * @return The game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the tile at the specified coordinates on the board.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile at the specified coordinates.
     */
    public Tile getTile(int x, int y) {
        return board.getTile(x, y);
    }

    /**
     * Moves a player's piece from the current tile to the new tile.
     *
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isMoveValid(Tile currentTile, Tile newTile) {
        Piece playerPiece = currentTile.getPiece();

        if (playerPiece == null || !playerPiece.canMove(currentTile, newTile)
                || currentPlayer.isYellow() != playerPiece.isYellow()) {
            return false;
        }

        if (newTile.getPiece() != null && newTile.getPiece().isYellow() == playerPiece.isYellow()) {
            return false;
        }

        return true;
    }

    /**
     * Sets the player's move from the current tile to the new tile.
     * After the move, flips the game board and updates the current player.
     *
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     */
    public void setPlayerMove(Tile currentTile, Tile newTile) {
        Piece piece = currentTile.getPiece();

        if (isMoveValid(currentTile, newTile)) {
            removeCaptured(newTile); // changes this function name maybe
            newTile.setPiece(piece);
            currentTile.setPiece(null);
            currentPlayer = playerManager.getNextPlayer();
            Board.flipBoard();
        }
    }

    private void removeCaptured(Tile tile) {
        Piece capturedPiece = tile.getPiece();
        if (capturedPiece != null) {
            tile.setPiece(null);
            capturedPiece.setCaptured();

            if (capturedPiece instanceof Sun) {
                playerManager.gameOver(capturedPiece.isYellow());
                // System.out.println("success");
            }
            ;

        }
    }

}