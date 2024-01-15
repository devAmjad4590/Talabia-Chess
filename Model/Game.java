package Model;

import Model.Pieces.Piece;
import Model.Pieces.Point;
import Model.Pieces.Sun;

/**
 * The `Game` class represents the main logic and state of a game.
 * It is implemented by Amgad Elrashid Gurashi Eltayeb.
 */
public class Game {
    private Board board; // The game board
    private PlayerManager playerManager; // The player manager
    private Player currentPlayer; // The current player
    private boolean gameOver = false; // True if the game is over, false otherwise
    private SaveManager saveManager;

    /**
     * Initializes the game by creating a player manager instance and a game board.
     */
    public Game() {
        init();
    }

    private void init() {
        board = Board.getInstance(); // object board created
        playerManager = new PlayerManager(); // playerManager created
        currentPlayer = playerManager.getCurrentPlayer(); //
        saveManager = new SaveManager();
    }

    /**
     * Gets the player manager.
     *
     * @return The player manager.
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
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
                || currentPlayer.isYellow() != playerPiece.isYellow()
                || currentTile == newTile) {
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
        occupyTile(currentTile, newTile); // moves the piece to the new tile
        switchPoint(newTile); // checks if the point piece made it to the first or last row
        currentPlayer = playerManager.getNextPlayer();
        Board.flipBoard();
    }

    public void switchPoint(Tile current) {
        Piece piece = current.getPiece();
        if (current.getX() == 5 || current.getX() == 0 && piece instanceof Point) {
            Point point = (Point) piece;
            point.switchMovement();
        }
    } 

    public void swapPieces(Piece piece1, Piece piece2){
     

    }
    
    

    /**
     * Removes the captured piece from the board.
     *
     * @param newTile The tile where the captured piece is located.
     */
    private void occupyTile(Tile currentTile, Tile newTile) {
        Piece piece = currentTile.getPiece();
        Piece capturedPiece = newTile.getPiece();
        newTile.setPiece(piece);
        currentTile.setPiece(null);
        Board.getMap().put(piece, newTile);
        if (capturedPiece != null) {
            Board.getMap().remove(capturedPiece); // needs testing
            isSunCaptured(capturedPiece);
        }
    }
    public boolean isSunCaptured(Piece piece) {
        if (piece instanceof Sun) {
            playerManager.setLoser(piece.isYellow());
            gameOver = true;
            return true;
        }
        return false;
    }
    // resigns the current player
    public void resign() {
        playerManager.setLoser(currentPlayer.isYellow());
        gameOver = true;
        nextGame();
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Resets the game and the scores completely.
     */
    public void resetAll() {
        Board.resetBoard();
        playerManager.reset();
        gameOver = false;
        init();
    }

    /**
     * Resets the game state for next Round
     */
    public void nextGame() {
        gameOver = false;
        Board.resetBoard();
        playerManager.resetTurn();
        currentPlayer = playerManager.getCurrentPlayer();

    }

    public void save(){
        saveManager.saveGame();
    }
}
